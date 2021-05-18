package br.com.os.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Contrutores da superclasse
    public DataIntegratyViolationException(String message) {
        super(message);
    }

    // A causa do erro
    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
