package com.centrodeportivo.reservas.model.abstracto;

import com.centrodeportivo.reservas.model.enums.UserRole;
import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

@Data
public abstract class User implements CsvEntity {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private UserRole rol;

    public abstract double calcularDescuento(double tarifaBase);
}
