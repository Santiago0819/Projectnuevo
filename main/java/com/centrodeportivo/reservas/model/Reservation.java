package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.enums.ReserveStatus;
import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Reservation implements CsvEntity {

    private Long id;
    private Long usuarioId;
    private Long instalacionId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double precioTotal;
    private ReserveStatus estado = ReserveStatus.PENDIENTE;
    private List<Long> equiposIds = new ArrayList<>();

    public boolean seSolapaCon(Reservation otra) {
        if (!this.instalacionId.equals(otra.instalacionId)) return false;
        if (!this.fecha.equals(otra.fecha)) return false;
        return this.horaInicio.isBefore(otra.horaFin)
                && otra.horaInicio.isBefore(this.horaFin);
    }
}
