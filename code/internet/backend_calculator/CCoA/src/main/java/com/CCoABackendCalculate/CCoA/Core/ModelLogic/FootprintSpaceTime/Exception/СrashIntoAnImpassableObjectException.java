package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Footprint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class СrashIntoAnImpassableObjectException extends Exception {
    private static final Logger LOGGER = LogManager.getLogger(СrashIntoAnImpassableObjectException.class);

    private double timeAdding;
    private Footprint footprint;
    public СrashIntoAnImpassableObjectException(double timeAdding, Footprint footprint) {
        this.timeAdding = timeAdding;
        this.footprint = footprint;

        LOGGER.debug("СrashIntoAnImpassableObjectException timeAdding: {}, Footprint: {}", timeAdding, footprint);
    }

    public СrashIntoAnImpassableObjectException() {
        LOGGER.debug("СrashIntoAnImpassableObjectException");
    }
}
