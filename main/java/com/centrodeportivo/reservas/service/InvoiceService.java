package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Invoice;
import com.centrodeportivo.reservas.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> listar() {
        return invoiceRepository.findAll().stream()
                .sorted(Comparator.comparing(Invoice::getId))
                .toList();
    }

    public Invoice obtener(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Factura no encontrada"));
    }

    public Invoice crear(Invoice f) {
        return invoiceRepository.save(f);
    }

    public Invoice actualizar(Long id, Invoice f) {
        f.setId(id);
        return invoiceRepository.save(f);
    }

    public void eliminar(Long id) {
        invoiceRepository.deleteById(id);
    }
}
