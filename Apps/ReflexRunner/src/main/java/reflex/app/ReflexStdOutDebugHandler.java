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
package reflex.app;

import org.apache.log4j.Logger;

import reflex.DebugLevel;
import reflex.IReflexDebugHandler;

public class ReflexStdOutDebugHandler implements IReflexDebugHandler {
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void debugOut(int arg0, DebugLevel arg1, String arg2) {
        String message = "Line: " + arg0 + ", " + arg2;
        switch (arg1) {
        case SPAM:
            log.info(message);
            break;
        case INFO:
            log.info(message);
            break;
        case WARNING:
            log.warn(message);
            break;
        case ERROR:
            log.error(message);
            break;
        }
    }

    @Override
    public void statementReached(int arg0, DebugLevel arg1, String arg2) {
        debugOut(arg0, arg1, arg2);
    }

    @Override
    public boolean hasCapability() {
        return true;
    }

}
