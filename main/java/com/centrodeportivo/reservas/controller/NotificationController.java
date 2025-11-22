package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Notification;
import com.centrodeportivo.reservas.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Notification> listarPorUsuario(@PathVariable Long usuarioId) {
        return notificationService.listarPorUsuario(usuarioId);
    }

    @PostMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification crear(@PathVariable Long usuarioId, @RequestParam String mensaje) {
        return notificationService.crear(usuarioId, mensaje);
    }

    @PostMapping("/{id}/leer")
    public Notification marcarLeida(@PathVariable Long id) {
        return notificationService.marcarLeida(id);
    }
}
