package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Installation;
import com.centrodeportivo.reservas.repository.InstallationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalacionService {

    private final InstallationRepository installationRepository;

    public List<Installation> listar() {
        return installationRepository.findAll().stream()
                .sorted(Comparator.comparing(Installation::getId))
                .toList();
    }

    public Installation obtener(Long id) {
        return installationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instalación no encontrada"));
    }

    public Installation crear(Installation i) {
        return installationRepository.save(i);
    }

    public Installation actualizar(Long id, Installation i) {
        i.setId(id);
        return installationRepository.save(i);
    }

    public void eliminar(Long id) {
        installationRepository.deleteById(id);
    }
}
