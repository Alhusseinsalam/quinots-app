package dev.husein.quinots.exception;

public class QuinotsException extends RuntimeException {
    public QuinotsException() {
        super();
    }

    public QuinotsException(String msg) {
        super(msg);
    }

    public QuinotsException(String msg, Throwable e) {
        super(msg, e);
    }
}
