package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Socio;
import com.centrodeportivo.reservas.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @GetMapping
    public List<Socio> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return socioService.listar(page, size);
    }

    @GetMapping("/{id}")
    public Socio obtener(@PathVariable Long id) {
        return socioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Socio crear(@RequestBody Socio socio) {
        return socioService.crear(socio);
    }

    @PutMapping("/{id}")
    public Socio actualizar(@PathVariable Long id, @RequestBody Socio socio) {
        return socioService.actualizar(id, socio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
    }
}
