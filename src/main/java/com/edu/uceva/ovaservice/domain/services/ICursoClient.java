package com.edu.uceva.ovaservice.domain.services;

import com.edu.uceva.ovaservice.domain.model.CursoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "Curso-Service")
public interface ICursoClient {
    @GetMapping("api/v1/curso-service/cursos")
    Map<String, List<CursoDTO>> idcurso();
}
