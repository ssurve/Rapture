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
package rapture.dsl.serfun;

import static java.lang.Double.isNaN;

import java.util.List;

import com.google.common.base.Preconditions;

import rapture.common.Hose;
import rapture.common.SeriesValue;

public class SkipNaNHose extends SimpleHose {
    
    public SkipNaNHose(HoseArg in) {
        super(in);
    }

    @Override
    public String getName() {
        return "skipNaN()";
    }

    @Override
    public void pushValue(SeriesValue v) { 
        if (v.isDouble() && !isNaN(v.asDouble())) {
            downstream.pushValue(v);
        }
        // else drop
    }

    @Override
    public SeriesValue pullValue() {
        while(true) {
            SeriesValue v = upstream.pullValue();
            if (v == null || (v.isDouble() && !isNaN(v.asDouble())) || v.isLong()) {
                return v;
            } 
        }
    }

    public static class Factory implements HoseFactory {
        @Override
        public Hose make(List<HoseArg> args) {
            Preconditions.checkArgument(args.size() == 1, "Wrong number of arguments to skipNaN()");
            HoseArg input = args.get(0);
            Preconditions.checkArgument(input.isSeries());
            return new SkipNaNHose(input);
        }        
    }
}
