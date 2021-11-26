package dev.husein.quinots.exception;

public class IllegalQueryParamException extends IllegalArgumentException {
    public IllegalQueryParamException() {
        super();
    }

    public IllegalQueryParamException(String msg) {
        super(msg);
    }
}
