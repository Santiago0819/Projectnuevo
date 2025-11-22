package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.dto.ReservationRequestDTO;
import com.centrodeportivo.reservas.dto.ReservationResponseDTO;
import com.centrodeportivo.reservas.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationResponseDTO> listar() {
        return reservationService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO crear(@RequestBody ReservationRequestDTO dto) {
        return reservationService.crear(dto);
    }

    @PostMapping("/{id}/cancelar")
    public ReservationResponseDTO cancelar(@PathVariable Long id) {
        return reservationService.cancelar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        reservationService.eliminar(id);
    }
}
