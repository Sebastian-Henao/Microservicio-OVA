package com.edu.uceva.ovaservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ovas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "La descripcion no puede estar vacia")
    @Size(max = 255, message = "La descripcion no puede tener mas de 255 caracteres")
    private String description;

    @NotNull(message = "El ID del curso no puede ser nulo")
    @Positive(message = "El ID del curso debe ser un numero positivo")
    @Column(nullable = false)
    private Long idcurso;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;
}
