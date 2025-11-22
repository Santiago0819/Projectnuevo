package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Equipment;
import com.centrodeportivo.reservas.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> listar() {
        return equipmentService.listar();
    }

    @GetMapping("/{id}")
    public Equipment obtener(@PathVariable Long id) {
        return equipmentService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment crear(@RequestBody Equipment e) {
        return equipmentService.crear(e);
    }

    @PutMapping("/{id}")
    public Equipment actualizar(@PathVariable Long id, @RequestBody Equipment e) {
        return equipmentService.actualizar(id, e);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        equipmentService.eliminar(id);
    }
}
