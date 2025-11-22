package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Service;
import com.centrodeportivo.reservas.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicioService {

    private final ServiceRepository serviceRepository;

    public List<Service> listar() {
        return serviceRepository.findAll().stream()
                .sorted(Comparator.comparing(Service::getId))
                .toList();
    }

    public Service obtener(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servicio no encontrado"));
    }

    public Service crear(Service s) {
        return serviceRepository.save(s);
    }

    public Service actualizar(Long id, Service s) {
        s.setId(id);
        return serviceRepository.save(s);
    }

    public void eliminar(Long id) {
        serviceRepository.deleteById(id);
    }
}
