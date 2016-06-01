package com.lapsa.insurance.mesenger;

public class NotificationFailed extends Exception {

    private static final long serialVersionUID = -5063540628264203040L;

    public NotificationFailed() {
	super();
    }

    public NotificationFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotificationFailed(String message, Throwable cause) {
	super(message, cause);
    }

    public NotificationFailed(String message) {
	super(message);
    }

    public NotificationFailed(Throwable cause) {
	super(cause);
    }
}