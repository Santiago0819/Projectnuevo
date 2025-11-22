package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Equipment;
import com.centrodeportivo.reservas.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public List<Equipment> listar() {
        return equipmentRepository.findAll().stream()
                .sorted(Comparator.comparing(Equipment::getId))
                .toList();
    }

    public Equipment obtener(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
    }

    public Equipment crear(Equipment e) {
        return equipmentRepository.save(e);
    }

    public Equipment actualizar(Long id, Equipment e) {
        e.setId(id);
        return equipmentRepository.save(e);
    }

    public void eliminar(Long id) {
        equipmentRepository.deleteById(id);
    }
}
