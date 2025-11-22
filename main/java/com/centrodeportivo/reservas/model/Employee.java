package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.abstracto.User;
import com.centrodeportivo.reservas.model.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {

    private String cargo;
    private double salario;

    public Employee() {
        setRol(UserRole.EMPLEADO);
    }

    @Override
    public double calcularDescuento(double tarifaBase) {
        return 0.0;
    }
}
