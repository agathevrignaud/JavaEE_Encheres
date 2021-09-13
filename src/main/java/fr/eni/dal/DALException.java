package fr.eni.dal;

public class DALException extends Exception {
    public DALException() {
    }

    public DALException(String message) {
        super(String.format("%s", message));
    }

    public DALException(String message, Throwable cause) {
        super(String.format("%s", message), cause);
    }
}
