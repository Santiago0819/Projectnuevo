package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Equipment;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentRepository extends CsvRepository<Equipment> {

    public EquipmentRepository() {
        super("equipos.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;nombre;disponible";
    }

    @Override
    protected String toCsvRow(Equipment e) {
        return String.join(";",
                String.valueOf(e.getId()),
                e.getNombre(),
                String.valueOf(e.isDisponible())
        );
    }

    @Override
    protected Equipment fromCsvRow(String row) {
        String[] p = row.split(";");
        Equipment e = new Equipment();
        e.setId(Long.parseLong(p[0]));
        e.setNombre(p[1]);
        e.setDisponible(Boolean.parseBoolean(p[2]));
        return e;
    }
}
