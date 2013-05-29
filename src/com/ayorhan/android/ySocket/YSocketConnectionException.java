package com.ayorhan.android.ySocket;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketConnectionException extends Exception {
    public YSocketConnectionException() {
    }

    public YSocketConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public YSocketConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public YSocketConnectionException(Throwable throwable) {
        super(throwable);
    }
}
