package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Service;
import com.centrodeportivo.reservas.service.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicioService servicioService;

    @GetMapping
    public List<Service> listar() {
        return servicioService.listar();
    }

    @GetMapping("/{id}")
    public Service obtener(@PathVariable Long id) {
        return servicioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Service crear(@RequestBody Service s) {
        return servicioService.crear(s);
    }

    @PutMapping("/{id}")
    public Service actualizar(@PathVariable Long id, @RequestBody Service s) {
        return servicioService.actualizar(id, s);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
    }
}
