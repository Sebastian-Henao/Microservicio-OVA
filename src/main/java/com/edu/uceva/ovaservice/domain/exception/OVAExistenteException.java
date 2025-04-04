package com.edu.uceva.ovaservice.domain.exception;

public class OVAExistenteException extends RuntimeException {
    public OVAExistenteException(String nombre) {
        super("El OVA con nombre " + nombre + " ya existe");
    }
}
