package com.edu.uceva.ovaservice.domain.services;

import com.edu.uceva.ovaservice.domain.model.Ovas;
import com.edu.uceva.ovaservice.domain.repositories.IOvasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa los metodos de la interfaz IOvasService para realizar las operaciones
 * de negocio sobre la entidad Ovas
 */
@Service
public class OvasServiceImpl implements IOvasService {
    IOvasRepository ovasRepository;
    public OvasServiceImpl(IOvasRepository ovasRepository) {this.ovasRepository = ovasRepository;}

    @Override
    @Transactional
    public Ovas save(Ovas ovas) {return ovasRepository.save(ovas);}

    @Override
    @Transactional
    public void delete(Ovas ovas) {ovasRepository.delete(ovas);}

    @Override
    @Transactional(readOnly = true)
    public Optional<Ovas> findById(Long id){return ovasRepository.findById(id);}

    @Override
    @Transactional
    public Ovas update(Ovas ovas) {return ovasRepository.save(ovas);}

    @Override
    @Transactional(readOnly = true)
    public List<Ovas> findAll() {return ovasRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Page<Ovas> findAll(Pageable pageable) {return ovasRepository.findAll(pageable);}
}
