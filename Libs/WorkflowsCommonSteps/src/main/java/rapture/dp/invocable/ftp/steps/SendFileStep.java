/**
 * Copyright (C) 2011-2015 Incapture Technologies LLC
 *
 * This is an autogenerated license statement. When copyright notices appear below
 * this one that copyright supercedes this statement.
 *
 * Unless required by applicable law or agreed to in writing, software is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * Unless explicit permission obtained in writing this software cannot be distributed.
 */
package rapture.dp.invocable.ftp.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import rapture.common.CallingContext;
import rapture.common.RaptureURI;
import rapture.common.api.DecisionApi;
import rapture.common.dp.AbstractInvocable;
import rapture.common.exception.ExceptionToString;
import rapture.common.impl.jackson.JacksonUtil;
import rapture.ftp.common.Connection;
import rapture.ftp.common.FTPRequest;
import rapture.ftp.common.FTPRequest.Action;
import rapture.ftp.common.FTPRequest.Status;
import rapture.ftp.common.SFTPConnection;
import rapture.kernel.Kernel;
import rapture.kernel.dp.ExecutionContextUtil;

public class SendFileStep extends AbstractInvocable {
    private static final Logger log = Logger.getLogger(SendFileStep.class);

    DecisionApi decision;
    public SendFileStep(String workerUri, String stepName) {
        super(workerUri, stepName);
        decision = Kernel.getDecision();
    }

    public String renderTemplate(CallingContext ctx, String template) {
        String workOrder = new RaptureURI(getWorkerURI()).toShortString();
        return ExecutionContextUtil.evalTemplateECF(ctx, workOrder, template, new HashMap<String, String>());
    }

    @Override
    public String invoke(CallingContext ctx) {
        try {
            Kernel.getDecision().setContextLiteral(ctx, getWorkerURI(), "STEPNAME", getStepName());

            String configUri = decision.getContextValue(ctx, getWorkerURI(), "FTP_CONFIGURATION");
            if (configUri == null) {
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Problem in SendFileStep " + getStepName() + " - parameter FTP_CONFIGURATION is not set",
                        true);
                decision.setContextLiteral(ctx, getWorkerURI(), getStepName(), "FTP_CONFIGURATION not set");
                return getErrorTransition();
            }

            if (!Kernel.getDoc().docExists(ctx, configUri)) {
                decision.setContextLiteral(ctx, getWorkerURI(), getStepName(), "Cannot load FTP_CONFIGURATION from " + configUri);
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(),
                        "Problem in SendFileStep " + getStepName() + " - Cannot load FTP_CONFIGURATION from " + configUri, true);
                return getErrorTransition();
            }

            String copy = StringUtils.stripToNull(decision.getContextValue(ctx, getWorkerURI(), "COPY_FILES"));
            if (copy == null) {
                decision.setContextLiteral(ctx, getWorkerURI(), getStepName(), "No files to copy");
                return getNextTransition();
            }

            Map<String, Object> map = JacksonUtil.getMapFromJson(renderTemplate(ctx, copy));

            String retval = getNextTransition();
            int failCount = 0;
            Connection connection = new SFTPConnection(configUri).setContext(ctx);
            List<FTPRequest> requests = new ArrayList<>();
            for (Entry<String, Object> e : map.entrySet()) {
                FTPRequest request = new FTPRequest(Action.WRITE).setLocalName(e.getKey()).setRemoteName(e.getValue().toString());
                connection.doAction(request);
                if (!request.getStatus().equals(Status.SUCCESS)) {
                    retval = getFailTransition();
                    decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Unable to send " + e.getKey(), true);
                    failCount++;
                }
                requests.add(request);
            }
            if (failCount > 0) {
                decision.writeWorkflowAuditEntry(ctx, getWorkerURI(), "Unable to send " + failCount + " of " + map.size() + " files)", true);
            }
            decision.setContextLiteral(ctx, getWorkerURI(), getStepName(), "Sent " + (map.size() - failCount) + " of " + map.size() + " files)");
            decision.setContextLiteral(ctx, getWorkerURI(), getStepName() + "Result", JacksonUtil.jsonFromObject(requests));
            return retval;
        } catch (Exception e) {
            Kernel.getDecision().setContextLiteral(ctx, getWorkerURI(), getStepName(), "Unable to send files : " + e.getLocalizedMessage());
            Kernel.getDecision().setContextLiteral(ctx, getWorkerURI(), getStepName() + "Error", ExceptionToString.summary(e));
            decision.writeWorkflowAuditEntry(ctx, getWorkerURI(),
                    "Problem in GetFileStep " + getStepName() + " - error is " + ExceptionToString.getRootCause(e).getLocalizedMessage(), true);
            return getErrorTransition();
        }
    }

}
