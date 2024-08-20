package dev.paulosouza.truco.exception;

public class TrucoExcpetion extends RuntimeException {

    private final String cause;

    public TrucoExcpetion(String message) {
        super(message);
        this.cause = message;
    }

}
