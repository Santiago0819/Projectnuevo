package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Socio;
import com.centrodeportivo.reservas.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public List<Socio> listar(int page, int size) {
        List<Socio> todos = socioRepository.findAll().stream()
                .sorted(Comparator.comparing(Socio::getId))
                .toList();
        int from = Math.min(page * size, todos.size());
        int to = Math.min(from + size, todos.size());
        return todos.subList(from, to);
    }

    public Socio obtener(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Socio no encontrado"));
    }

    public Socio crear(Socio socio) {
        return socioRepository.save(socio);
    }

    public Socio actualizar(Long id, Socio socio) {
        socio.setId(id);
        return socioRepository.save(socio);
    }

    public void eliminar(Long id) {
        socioRepository.deleteById(id);
    }
}
