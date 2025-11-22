package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Payment;
import com.centrodeportivo.reservas.model.enums.PaymentMethod;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PaymentRepository extends CsvRepository<Payment> {

    public PaymentRepository() {
        super("pagos.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;reservaId;metodo;monto;fechaHora";
    }

    @Override
    protected String toCsvRow(Payment p) {
        return String.join(";",
                String.valueOf(p.getId()),
                String.valueOf(p.getReservaId()),
                p.getMetodo() == null ? "" : p.getMetodo().name(),
                String.valueOf(p.getMonto()),
                p.getFechaHora() == null ? "" : p.getFechaHora().toString()
        );
    }

    @Override
    protected Payment fromCsvRow(String row) {
        String[] p = row.split(";");
        Payment payment = new Payment();
        payment.setId(Long.parseLong(p[0]));
        payment.setReservaId(Long.parseLong(p[1]));
        if (!p[2].isBlank()) {
            payment.setMetodo(PaymentMethod.valueOf(p[2]));
        }
        payment.setMonto(Double.parseDouble(p[3]));
        if (p.length > 4 && !p[4].isBlank()) {
            payment.setFechaHora(LocalDateTime.parse(p[4]));
        }
        return payment;
    }
}
