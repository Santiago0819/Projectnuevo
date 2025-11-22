package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends CsvRepository<Employee> {

    public EmployeeRepository() {
        super("empleados.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;nombre;email;telefono;cargo;salario";
    }

    @Override
    protected String toCsvRow(Employee e) {
        return String.join(";",
                String.valueOf(e.getId()),
                e.getNombre(),
                e.getEmail(),
                e.getTelefono(),
                e.getCargo() == null ? "" : e.getCargo(),
                String.valueOf(e.getSalario())
        );
    }

    @Override
    protected Employee fromCsvRow(String row) {
        String[] p = row.split(";");
        Employee e = new Employee();
        e.setId(Long.parseLong(p[0]));
        e.setNombre(p[1]);
        e.setEmail(p[2]);
        e.setTelefono(p[3]);
        e.setCargo(p[4]);
        e.setSalario(Double.parseDouble(p[5]));
        return e;
    }
}
