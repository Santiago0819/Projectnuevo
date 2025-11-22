package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Socio;
import org.springframework.stereotype.Repository;

@Repository
public class SocioRepository extends CsvRepository<Socio> {

    public SocioRepository() {
        super("socios.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;nombre;email;telefono;nivelMembresia;maxEquiposPrestamo";
    }

    @Override
    protected String toCsvRow(Socio s) {
        return String.join(";",
                String.valueOf(s.getId()),
                s.getNombre(),
                s.getEmail(),
                s.getTelefono(),
                s.getNivelMembresia() == null ? "" : s.getNivelMembresia(),
                String.valueOf(s.getMaxEquiposPrestamo())
        );
    }

    @Override
    protected Socio fromCsvRow(String row) {
        String[] p = row.split(";");
        Socio s = new Socio();
        s.setId(Long.parseLong(p[0]));
        s.setNombre(p[1]);
        s.setEmail(p[2]);
        s.setTelefono(p[3]);
        s.setNivelMembresia(p[4]);
        s.setMaxEquiposPrestamo(Integer.parseInt(p[5]));
        return s;
    }
}
