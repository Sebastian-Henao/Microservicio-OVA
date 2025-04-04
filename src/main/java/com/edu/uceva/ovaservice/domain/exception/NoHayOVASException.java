package com.edu.uceva.ovaservice.domain.exception;

public class NoHayOVASException extends RuntimeException {
    public NoHayOVASException() {super("No hay OVAS en la base de datos");}
}
