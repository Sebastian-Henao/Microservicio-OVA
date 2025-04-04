package com.edu.uceva.ovaservice.domain.services;

import com.edu.uceva.ovaservice.domain.model.Ovas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// Interface que define los metodos que se pueden realizar sobre la entidad Ovas
public interface IOvasService {
    Ovas save(Ovas ovas);
    void delete(Ovas ovas);
    Optional<Ovas> findById(Long id);
    Ovas update(Ovas ovas);
    List<Ovas> findAll();
    Page<Ovas> findAll(Pageable pageable);
}
