package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Reservation;
import com.centrodeportivo.reservas.model.enums.ReserveStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository extends CsvRepository<Reservation> {

    public ReservationRepository() {
        super("reservas.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;usuarioId;instalacionId;fecha;horaInicio;horaFin;precioTotal;estado;equiposIds";
    }

    @Override
    protected String toCsvRow(Reservation r) {
        String equipos = r.getEquiposIds().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return String.join(";",
                String.valueOf(r.getId()),
                String.valueOf(r.getUsuarioId()),
                String.valueOf(r.getInstalacionId()),
                r.getFecha().toString(),
                r.getHoraInicio().toString(),
                r.getHoraFin().toString(),
                String.valueOf(r.getPrecioTotal()),
                r.getEstado().name(),
                equipos
        );
    }

    @Override
    protected Reservation fromCsvRow(String row) {
        String[] p = row.split(";");
        Reservation r = new Reservation();
        r.setId(Long.parseLong(p[0]));
        r.setUsuarioId(Long.parseLong(p[1]));
        r.setInstalacionId(Long.parseLong(p[2]));
        r.setFecha(LocalDate.parse(p[3]));
        r.setHoraInicio(LocalTime.parse(p[4]));
        r.setHoraFin(LocalTime.parse(p[5]));
        r.setPrecioTotal(Double.parseDouble(p[6]));
        r.setEstado(ReserveStatus.valueOf(p[7]));
        if (p.length > 8 && !p[8].isBlank()) {
            List<Long> equiposIds = Arrays.stream(p[8].split(","))
                    .map(Long::parseLong).toList();
            r.setEquiposIds(equiposIds);
        }
        return r;
    }
}
