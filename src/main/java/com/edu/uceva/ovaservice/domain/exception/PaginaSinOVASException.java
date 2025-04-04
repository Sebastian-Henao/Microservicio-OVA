package com.edu.uceva.ovaservice.domain.exception;

public class PaginaSinOVASException extends RuntimeException {
    public PaginaSinOVASException(int page) {
        super("No hay OVAS en la pagina solicitada " + page);
    }
}
