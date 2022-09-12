package mvcproject.java11.crm.exception;

public class DatabaseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatabaseNotFoundException(String message) {
        super(message);
    }
}
