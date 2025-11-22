package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Payment;
import com.centrodeportivo.reservas.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<Payment> listar() {
        return paymentService.listar();
    }

    @GetMapping("/{id}")
    public Payment obtener(@PathVariable Long id) {
        return paymentService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment crear(@RequestBody Payment p) {
        return paymentService.crear(p);
    }

    @PutMapping("/{id}")
    public Payment actualizar(@PathVariable Long id, @RequestBody Payment p) {
        return paymentService.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        paymentService.eliminar(id);
    }
}
