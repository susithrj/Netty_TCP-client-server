package com.dfn.soupbin;

/**
 * SoupException, contains soup specific exceptions.
 */
public class SoupException extends Exception {
    public SoupException(final String message) {
        super(message);
    }

    public SoupException(final String message, final Throwable e) {
        super(message, e);
    }
}