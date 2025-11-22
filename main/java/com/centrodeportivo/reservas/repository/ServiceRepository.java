package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Service;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRepository extends CsvRepository<Service> {

    public ServiceRepository() {
        super("servicios.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;nombre;costoBase;descripcion";
    }

    @Override
    protected String toCsvRow(Service s) {
        return String.join(";",
                String.valueOf(s.getId()),
                s.getNombre(),
                String.valueOf(s.getCostoBase()),
                s.getDescripcion() == null ? "" : s.getDescripcion()
        );
    }

    @Override
    protected Service fromCsvRow(String row) {
        String[] p = row.split(";");
        Service s = new Service();
        s.setId(Long.parseLong(p[0]));
        s.setNombre(p[1]);
        s.setCostoBase(Double.parseDouble(p[2]));
        s.setDescripcion(p.length > 3 ? p[3] : "");
        return s;
    }
}
