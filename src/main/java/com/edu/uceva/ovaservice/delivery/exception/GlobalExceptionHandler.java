package com.edu.uceva.ovaservice.delivery.exception;

import com.edu.uceva.ovaservice.domain.exception.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String OVAS = "ovas";
    private static final String STATUS = "status";
    @ExceptionHandler(PaginaSinOVASException.class)
    public ResponseEntity<Map<String, Object>> handlepaginaSinOVASException(PaginaSinOVASException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "Numero de pagina invalido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoHayOVASException.class)
    public ResponseEntity<Map<String, Object>> handleNoHayOVASException(NoHayOVASException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "No hay OVAS en la base de datos");
        response.put(OVAS, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(OVANoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleOVANoEncontradoException(OVANoEncontradoException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put((ERROR), ex.getMessage());
        response.put(STATUS, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(OVAExistenteException.class)
    public ResponseEntity<Map<String, Object>> handleOVAExistenteException(OVAExistenteException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put((ERROR), ex.getMessage());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, "Error inesperado " + ex.getMessage());
        response.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "Error al consultar la Base de datos");
        response.put(ERROR, ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        response.put(ERROR, ex.getMessage());
        List<String> errors = ex.result.getFieldErrors()
                .stream()
                .map(err -> "El campo ' " + err.getField() + "' " + err.getDefaultMessage())
                .toList();
        response. put(ERROR, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}