package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Installation;
import com.centrodeportivo.reservas.service.InstalacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instalaciones")
@RequiredArgsConstructor
public class InstallationController {

    private final InstalacionService instalacionService;

    @GetMapping
    public List<Installation> listar() {
        return instalacionService.listar();
    }

    @GetMapping("/{id}")
    public Installation obtener(@PathVariable Long id) {
        return instalacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Installation crear(@RequestBody Installation i) {
        return instalacionService.crear(i);
    }

    @PutMapping("/{id}")
    public Installation actualizar(@PathVariable Long id, @RequestBody Installation i) {
        return instalacionService.actualizar(id, i);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        instalacionService.eliminar(id);
    }
}
