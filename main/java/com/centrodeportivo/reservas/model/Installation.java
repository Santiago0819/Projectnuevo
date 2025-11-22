package com.centrodeportivo.reservas.model;

import com.centrodeportivo.reservas.model.enums.InstallationType;
import com.centrodeportivo.reservas.repository.CsvEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Installation implements CsvEntity {

    private Long id;
    private String nombre;
    private InstallationType tipo;
    private double tarifaHora;
    private List<Long> equiposIds = new ArrayList<>();
}
