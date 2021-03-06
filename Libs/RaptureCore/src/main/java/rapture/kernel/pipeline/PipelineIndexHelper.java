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

import rapture.common.CallingContext;
import rapture.index.IndexHandler;
import rapture.kernel.ContextFactory;
import rapture.kernel.Kernel;
import rapture.table.IndexFactory;
import rapture.config.ConfigLoader;

/**
 * @author bardhi
 * @since 4/28/15.
 */
public class PipelineIndexHelper {
    private static final String indexUri;
    private static Logger log = Logger.getLogger(PipelineIndexHelper.class);

    static {
        final String authority = "pipelinetaskstatus";
        indexUri = "//" + authority + "/index";
    }

    public static void ensureIndexExists() {
        CallingContext ctx = ContextFactory.getKernelUser();
        String status = ConfigLoader.getConf().DefaultPipelineTaskStatus;
        log.info("Creating index with config " + status);
        Kernel.getIndex().createTable(ctx, indexUri, status);
    }

    public static IndexHandler createIndexHandler() {
        return Kernel.getIndex().getTrusted().getIndexHandler(indexUri);
    }
}
