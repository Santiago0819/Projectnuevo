package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification implements CsvEntity {

    private Long id;
    private Long usuarioId;
    private String mensaje;
    private LocalDateTime fechaHora;
    private boolean leida;
}
