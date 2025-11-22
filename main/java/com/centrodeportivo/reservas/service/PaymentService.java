package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Payment;
import com.centrodeportivo.reservas.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> listar() {
        return paymentRepository.findAll().stream()
                .sorted(Comparator.comparing(Payment::getId))
                .toList();
    }

    public Payment obtener(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pago no encontrado"));
    }

    public Payment crear(Payment p) {
        return paymentRepository.save(p);
    }

    public Payment actualizar(Long id, Payment p) {
        p.setId(id);
        return paymentRepository.save(p);
    }

    public void eliminar(Long id) {
        paymentRepository.deleteById(id);
    }
}
