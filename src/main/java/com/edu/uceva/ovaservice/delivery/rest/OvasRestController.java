package com.edu.uceva.ovaservice.delivery.rest;

import com.edu.uceva.ovaservice.domain.exception.NoHayOVASException;
import com.edu.uceva.ovaservice.domain.exception.OVANoEncontradoException;
import com.edu.uceva.ovaservice.domain.exception.PaginaSinOVASException;
import com.edu.uceva.ovaservice.domain.exception.ValidationException;
import com.edu.uceva.ovaservice.domain.model.Ovas;
import com.edu.uceva.ovaservice.domain.services.IOvasService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ova-service")
public class OvasRestController {
    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final IOvasService ovasService;
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "ova";
    private static final String OVAS = "ovas";

    // Inyeccion de dependencia del servicio que proporciona servicios de CRUD
    public OvasRestController(IOvasService ovasService) {this.ovasService = ovasService;}

    // Listar todos los OVAS
    @GetMapping("/ovas")
    public ResponseEntity<Map<String,Object>> getOvas(){
        List<Ovas> ovas = ovasService.findAll();
        if (ovas.isEmpty()) {
            throw new NoHayOVASException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(OVAS, ovas);
        return ResponseEntity.ok(response);
    }

    // Listar OVAS con paginacion
    @GetMapping("/ovas/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        Page<Ovas> ovas = ovasService.findAll(pageable);
        if (ovas.isEmpty()){
            throw new PaginaSinOVASException(page);
        }
        return ResponseEntity.ok(ovas);
    }

    //Crear un nuevo OVA pasando el objeto en el cuerpo de la peticion, usando validaciones
    @PostMapping("/ovas")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Ovas ovas, BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Ovas nuevoOva = ovasService.save(ovas);
        response.put(MENSAJE, "El OVA ha sido creado con exito");
        response.put(OVA, nuevoOva);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Eliminar un OVA pasando el objeto en el cuerpo de la peticion
    @DeleteMapping("/ovas")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Ovas ovas){
        ovasService.findById(ovas.getId())
                .orElseThrow(() -> new OVANoEncontradoException(ovas.getId()));
        ovasService.delete(ovas);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El OVA ha sido eliminado con exito");
        response.put(OVA, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un OVA pasando el objeto en el cuerpo de la peticion
     * @param ovas: Objeto Ovas que se va a actualizar
     */
    @PutMapping("/ovas")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Ovas ovas, BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
        ovasService.findById(ovas.getId())
                .orElseThrow(() -> new OVANoEncontradoException(ovas.getId()));
        Map<String, Object> response = new HashMap<>();
        Ovas ovaActualizado = ovasService.update(ovas);
        response.put(MENSAJE, "El OVA ha sido actualizado con exito");
        response.put(OVA, ovaActualizado);
        return ResponseEntity.ok(response);
    }

    // Obtener un OVA por su ID
    @GetMapping("/ovas/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        Ovas ovas = ovasService.findById(id)
                .orElseThrow(() -> new OVANoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El OVA ha sido encontrado con exito");
        response.put(OVA, ovas);
        return ResponseEntity.ok(response);
    }
}
