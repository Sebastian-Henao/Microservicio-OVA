package com.edu.uceva.ovaservice.domain.repositories;

import com.edu.uceva.ovaservice.domain.model.Ovas;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que hereda de CrudRepository para realizar las operaciones de CRUD sobre la entidad Ovas
public interface IOvasRepository extends JpaRepository<Ovas, Long> {
}
