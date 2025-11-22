package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.enums.PaymentMethod;
import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment implements CsvEntity {

    private Long id;
    private Long reservaId;
    private PaymentMethod metodo;
    private double monto;
    private LocalDateTime fechaHora;
}
