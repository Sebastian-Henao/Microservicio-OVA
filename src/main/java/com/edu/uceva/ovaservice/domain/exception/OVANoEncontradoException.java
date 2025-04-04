package com.edu.uceva.ovaservice.domain.exception;

public class OVANoEncontradoException extends RuntimeException {
    public OVANoEncontradoException(Long id) {super("El OVA con id " + id + " no fue encontrado");}
}
