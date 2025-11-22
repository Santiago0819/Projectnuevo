package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Notification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class NotificationRepository extends CsvRepository<Notification> {

    public NotificationRepository() {
        super("notificaciones.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;usuarioId;mensaje;fechaHora;leida";
    }

    @Override
    protected String toCsvRow(Notification n) {
        return String.join(";",
                String.valueOf(n.getId()),
                String.valueOf(n.getUsuarioId()),
                n.getMensaje(),
                n.getFechaHora() == null ? "" : n.getFechaHora().toString(),
                String.valueOf(n.isLeida())
        );
    }

    @Override
    protected Notification fromCsvRow(String row) {
        String[] p = row.split(";");
        Notification n = new Notification();
        n.setId(Long.parseLong(p[0]));
        n.setUsuarioId(Long.parseLong(p[1]));
        n.setMensaje(p[2]);
        if (p.length > 3 && !p[3].isBlank()) {
            n.setFechaHora(LocalDateTime.parse(p[3]));
        }
        n.setLeida(Boolean.parseBoolean(p[4]));
        return n;
    }
}
