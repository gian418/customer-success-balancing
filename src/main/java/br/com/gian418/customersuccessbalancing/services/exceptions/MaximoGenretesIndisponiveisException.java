package br.com.gian418.customersuccessbalancing.services.exceptions;

public class MaximoGenretesIndisponiveisException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MaximoGenretesIndisponiveisException(String message) {
        super(message);
    }

    public MaximoGenretesIndisponiveisException(String message, Throwable cause) {
        super(message, cause);
    }
}
