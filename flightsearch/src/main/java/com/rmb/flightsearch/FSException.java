package com.rmb.flightsearch;

public class FSException extends RuntimeException {

    private static final long serialVersionUID = -6202201587669897628L;

    public FSException(final String message) {
        super(message);
    }

    public static FSException withMessage(final String message) {
        Logger.report("Exception: " + message);
        return new FSException(message);
    }
}
