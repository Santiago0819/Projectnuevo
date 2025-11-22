package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.abstracto.User;
import com.centrodeportivo.reservas.model.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Socio extends User {

    private String nivelMembresia; // GOLD, SILVER, etc.
    private int maxEquiposPrestamo = 3;

    public Socio() {
        setRol(UserRole.SOCIO);
    }

    @Override
    public double calcularDescuento(double tarifaBase) {
        return switch (nivelMembresia) {
            case "GOLD" -> tarifaBase * 0.20;
            case "SILVER" -> tarifaBase * 0.10;
            default -> 0.0;
        };
    }
}
