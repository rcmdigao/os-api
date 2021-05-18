package br.com.os.exceptions;

/*  */
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Contrutores da superclasse
    public ObjectNotFoundException(String message) {
        super(message);
    }

    // A causa do erro
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
