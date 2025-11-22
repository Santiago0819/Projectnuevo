package com.centrodeportivo.reservas.dto;

import com.centrodeportivo.reservas.model.enums.ReserveStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponseDTO(
        Long id,
        Long usuarioId,
        Long instalacionId,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        double precioTotal,
        ReserveStatus estado
) {}
