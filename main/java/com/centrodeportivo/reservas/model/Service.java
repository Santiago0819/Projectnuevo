package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

@Data
public class Service implements CsvEntity {

    private Long id;
    private String nombre;
    private double costoBase;
    private String descripcion;
}
