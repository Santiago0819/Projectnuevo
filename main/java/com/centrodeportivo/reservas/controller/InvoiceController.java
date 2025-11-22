package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Invoice;
import com.centrodeportivo.reservas.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> listar() {
        return invoiceService.listar();
    }

    @GetMapping("/{id}")
    public Invoice obtener(@PathVariable Long id) {
        return invoiceService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice crear(@RequestBody Invoice f) {
        return invoiceService.crear(f);
    }

    @PutMapping("/{id}")
    public Invoice actualizar(@PathVariable Long id, @RequestBody Invoice f) {
        return invoiceService.actualizar(id, f);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        invoiceService.eliminar(id);
    }
}
