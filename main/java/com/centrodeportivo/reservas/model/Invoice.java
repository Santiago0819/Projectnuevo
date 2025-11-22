package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.interfaces.Pagable;
import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

@Data
public class Invoice implements CsvEntity, Pagable {

    private Long id;
    private Long reservaId;
    private Long pagoId;
    private double subtotal;
    private double descuento;
    private double total;

    @Override
    public double calcularTotal() {
        return subtotal - descuento;
    }
}
