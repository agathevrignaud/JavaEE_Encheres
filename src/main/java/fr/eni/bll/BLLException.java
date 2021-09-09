package fr.eni.bll;

public class BLLException extends Exception {
    public BLLException() {
    }

    public BLLException(String message) {
        super(String.format("%s", message));
    }

    public BLLException(String message, Throwable cause) {
        super(String.format("%s", message), cause);
    }
}
