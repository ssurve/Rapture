/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2016 Incapture Technologies LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package rapture.kernel.pipeline;

import org.apache.log4j.Logger;

import rapture.common.RapturePipelineTask;
import rapture.common.impl.jackson.JacksonUtil;
import rapture.common.mime.MimeRaptureAudit;
import rapture.exchange.QueueHandler;
import rapture.kernel.ContextFactory;
import rapture.kernel.Kernel;

public class RaptureAuditHandler implements QueueHandler {
    private static final Logger log = Logger.getLogger(RaptureAuditHandler.class);
    private final PipelineTaskStatusManager statusManager;

    public RaptureAuditHandler() {
        statusManager = new PipelineTaskStatusManager();
    }

    @Override
    public boolean handleMessage(String tag, String routing, String contentType, RapturePipelineTask task) {
        String content = task.getContent();
        log.info(Messages.getString("RaptureAuditHandler.attemptRun")); //$NON-NLS-1$
        try {

            MimeRaptureAudit audit = JacksonUtil.objectFromJson(content, MimeRaptureAudit.class);
            statusManager.startRunning(task);
            log.info(Messages.getString("RaptureAuditHandler.writeAudit")); //$NON-NLS-1$
            Kernel.getAudit().writeAuditEntry(ContextFactory.getKernelUser(), "//" + audit.getName(), audit.getCategory(), audit.getLevel(), audit.getMessage
                    ());
            statusManager.finishRunningWithSuccess(task);
        } catch (Exception e) {
            log.error(Messages.getString("RaptureAuditHandler.errorWriting"), //$NON-NLS-1$
                    e);
            statusManager.finishRunningWithFailure(task);
        }
        return true;
    }

    @Override
    public String toString() {
        return "RaptureAuditHandler [statusManager=" + statusManager + "]";
    }

}
