package rapture.dp.invocable.notification.steps;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.net.MediaType;

import rapture.common.CallingContext;
import rapture.common.RaptureURI;
import rapture.common.api.AdminApi;
import rapture.common.api.DecisionApi;
import rapture.common.dp.AbstractInvocable;
import rapture.common.exception.ExceptionToString;
import rapture.common.impl.jackson.JacksonUtil;
import rapture.kernel.Kernel;
import rapture.kernel.dp.ExecutionContextUtil;
import rapture.mail.EmailTemplate;
import rapture.mail.Mailer;
import rapture.mail.SMTPConfig;

// Should maybe subclass NotificationStep to provide other notification mechanisms?

// A step to notify the user
// * By email
// * By instant message app - Slack/What'sApp/Pidgin
// * text message?

public class NotificationStep extends AbstractInvocable {
    private static Logger log = Logger.getLogger(NotificationStep.class);
    DecisionApi decision;

    public NotificationStep(String workerUri, String stepName) {
        super(workerUri, stepName);
        decision = Kernel.getDecision();
    }

    @Override
    public String invoke(CallingContext ctx) {
	// Don't set STEPNAME here because we want the name of the preceding step
        // Can read config from a documemnt or pass as args
        AdminApi admin = Kernel.getAdmin();
        String types = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "NOTIFY_TYPE"));

        if (types == null) {
            decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - parameter NOTIFY_TYPE is not set", true);
            return getErrorTransition();
        }
        String retval = getNextTransition();
        for (String type : types.split("[, ]+")) {
            try {
                if (type.equalsIgnoreCase("SLACK") && !sendSlack(ctx)) {
                    decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Slack notification failed", true);
                    retval = getErrorTransition();
                }
            } catch (Exception e) {
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - slack notification failed", true);
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), ExceptionToString.summary(ExceptionToString.getRootCause(e)), true);
                retval = getErrorTransition();
            }
            try {
                if (type.equalsIgnoreCase("EMAIL") && !sendEmail(ctx)) {
                    decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Email notification failed", true);
                    retval = getErrorTransition();
                }
            } catch (Exception e) {
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - email notification failed", true);
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), ExceptionToString.summary(ExceptionToString.getRootCause(e)), true);
                retval = getErrorTransition();
            }
        }
        return retval;
    }

    private int doPost(URL url, byte[] body) throws IOException {
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setFixedLengthStreamingMode(body.length);
        http.setRequestProperty("Content-Type", MediaType.JSON_UTF_8.toString());
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.connect();
        try (OutputStream stream = http.getOutputStream()) {
            stream.write(body);
        }
        int response = http.getResponseCode();
        http.disconnect();
        return response;
    }

    public String renderTemplate(CallingContext ctx, String template) {
        String workOrder = new RaptureURI(getWorkerURI()).toShortString();
        return ExecutionContextUtil.evalTemplateECF(ctx, workOrder, template, new HashMap<String, String>());
    }

    private boolean sendSlack(CallingContext ctx) throws IOException {
        String message = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "MESSAGE_BODY"));
        // Legacy: use template if values are not set
        String templateName = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "MESSAGE_TEMPLATE"));
        String webhook = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "SLACK_WEBHOOK"));
        if (webhook == null) {
            decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - No webhook specified", true);
            return false;
        }

        if (message == null) {
            if (templateName == null) {
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - No message specified", true);
                return false;
            }
            EmailTemplate template = Mailer.getEmailTemplate(ctx, templateName);
            message = template.getMsgBody();
        }
        URL url = new URL(webhook);
        Map<String, String> slackNotification = new HashMap<>();
        slackNotification.put("text", renderTemplate(ctx, message));
        int response = doPost(url, JacksonUtil.bytesJsonFromObject(slackNotification));
        if (response == 200) return true;
        decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - HTTPPost returned code " + response, true);
        return false;
    }

    // Provided as an alternative to using admin.emailUser
    private boolean sendEmail(CallingContext ctx) throws AddressException, MessagingException {

        final SMTPConfig config = Mailer.getSMTPConfig();
        String message = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "MESSAGE_BODY"));
        String subject = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "MESSAGE_SUBJECT"));
        String recipientList = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "EMAIL_RECIPIENTS"));
        // Legacy: use template if values are not set
        String templateName = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "MESSAGE_TEMPLATE"));

        if (templateName != null) {
            EmailTemplate template = Mailer.getEmailTemplate(ctx, templateName);
            if (template != null) {
                if (message == null) message = template.getMsgBody();
                if (subject == null) subject = template.getSubject();
                if (recipientList == null) recipientList = template.getEmailTo();
            }
        }

        if (message == null) {
            decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - No message specified", true);
            return false;
        }

        if (recipientList == null) {
            decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in NotificationStep " + getStepName() + " - No recipient specified", true);
            return false;
        }

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", config.getHost());
        props.put("mail.smtp.port", config.getPort());
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getFrom(), config.getPassword());
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(config.getFrom()));
        String[] allRecipients = renderTemplate(ctx, recipientList).split("[, ]+");

        InternetAddress[] address = new InternetAddress[allRecipients.length];
        for (int i = 0; i < allRecipients.length; i++)
            address[i] = new InternetAddress(allRecipients[i]);

        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(renderTemplate(ctx, subject));
        msg.setContent(renderTemplate(ctx, message), MediaType.PLAIN_TEXT_UTF_8.toString());
        msg.setSentDate(new Date());
        Transport.send(msg);
        return true;
    }
}
