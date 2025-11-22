package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Installation;
import com.centrodeportivo.reservas.model.enums.InstallationType;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InstallationRepository extends CsvRepository<Installation> {

    public InstallationRepository() {
        super("instalaciones.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;nombre;tipo;tarifaHora;equiposIds";
    }

    @Override
    protected String toCsvRow(Installation i) {
        String equipos = i.getEquiposIds().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return String.join(";",
                String.valueOf(i.getId()),
                i.getNombre(),
                i.getTipo() == null ? "" : i.getTipo().name(),
                String.valueOf(i.getTarifaHora()),
                equipos
        );
    }

    @Override
    protected Installation fromCsvRow(String row) {
        String[] p = row.split(";");
        Installation i = new Installation();
        i.setId(Long.parseLong(p[0]));
        i.setNombre(p[1]);
        if (!p[2].isBlank()) {
            i.setTipo(InstallationType.valueOf(p[2]));
        }
        i.setTarifaHora(Double.parseDouble(p[3]));
        if (p.length > 4 && !p[4].isBlank()) {
            List<Long> equiposIds = Arrays.stream(p[4].split(","))
                    .map(Long::parseLong).toList();
            i.setEquiposIds(equiposIds);
        }
        return i;
    }
}
