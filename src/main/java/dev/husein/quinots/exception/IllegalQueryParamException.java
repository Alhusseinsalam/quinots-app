package io.hatchways.alhussein.backendassignment.exception;

public class IllegalQueryParamException extends IllegalArgumentException {
    public IllegalQueryParamException() {
        super();
    }

    public IllegalQueryParamException(String msg) {
        super(msg);
    }
}