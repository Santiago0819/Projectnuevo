package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Notification;
import com.centrodeportivo.reservas.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> listarPorUsuario(Long usuarioId) {
        return notificationRepository.findAll().stream()
                .filter(n -> n.getUsuarioId().equals(usuarioId))
                .sorted(Comparator.comparing(Notification::getFechaHora))
                .toList();
    }

    public Notification crear(Long usuarioId, String mensaje) {
        Notification n = new Notification();
        n.setUsuarioId(usuarioId);
        n.setMensaje(mensaje);
        n.setFechaHora(LocalDateTime.now());
        n.setLeida(false);
        return notificationRepository.save(n);
    }

    public Notification marcarLeida(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notificación no encontrada"));
        n.setLeida(true);
        return notificationRepository.save(n);
    }
}
