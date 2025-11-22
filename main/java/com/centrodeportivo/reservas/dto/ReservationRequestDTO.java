package com.centrodeportivo.reservas.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReservationRequestDTO {
    private Long usuarioId;
    private Long instalacionId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<Long> equiposIds;
}
