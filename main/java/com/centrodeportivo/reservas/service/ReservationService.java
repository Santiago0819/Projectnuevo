package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.dto.ReservationRequestDTO;
import com.centrodeportivo.reservas.dto.ReservationResponseDTO;
import com.centrodeportivo.reservas.exception.BusinessException;
import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Installation;
import com.centrodeportivo.reservas.model.Reservation;
import com.centrodeportivo.reservas.model.Socio;
import com.centrodeportivo.reservas.model.enums.ReserveStatus;
import com.centrodeportivo.reservas.repository.InstallationRepository;
import com.centrodeportivo.reservas.repository.ReservationRepository;
import com.centrodeportivo.reservas.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final InstallationRepository installationRepository;
    private final SocioRepository socioRepository;

    public List<ReservationResponseDTO> listar() {
        return reservationRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public ReservationResponseDTO crear(ReservationRequestDTO dto) {
        Socio socio = socioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Socio no encontrado"));

        Installation installation = installationRepository.findById(dto.getInstalacionId())
                .orElseThrow(() -> new NotFoundException("Instalación no encontrada"));

        if (dto.getEquiposIds() != null &&
                dto.getEquiposIds().size() > socio.getMaxEquiposPrestamo()) {
            throw new BusinessException("El socio supera el número máximo de equipos prestados");
        }

        Reservation nueva = new Reservation();
        nueva.setUsuarioId(dto.getUsuarioId());
        nueva.setInstalacionId(dto.getInstalacionId());
        nueva.setFecha(dto.getFecha());
        nueva.setHoraInicio(dto.getHoraInicio());
        nueva.setHoraFin(dto.getHoraFin());
        nueva.setEquiposIds(dto.getEquiposIds());

        List<Reservation> existentes = reservationRepository.findAll();
        for (Reservation r : existentes) {
            if (r.getEstado() != ReserveStatus.CANCELADA && nueva.seSolapaCon(r)) {
                throw new BusinessException("Ya existe una reserva para esa instalación en ese horario");
            }
        }

        long minutos = Duration.between(dto.getHoraInicio(), dto.getHoraFin()).toMinutes();
        if (minutos <= 0) {
            throw new BusinessException("El rango de horas es inválido");
        }

        double horas = minutos / 60.0;
        double tarifaBase = installation.getTarifaHora() * horas;
        double descuento = socio.calcularDescuento(tarifaBase);
        double total = tarifaBase - descuento;

        nueva.setPrecioTotal(total);
        nueva.setEstado(ReserveStatus.CONFIRMADA);

        Reservation guardada = reservationRepository.save(nueva);
        return toDTO(guardada);
    }

    public ReservationResponseDTO cancelar(Long id) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        r.setEstado(ReserveStatus.CANCELADA);
        reservationRepository.save(r);
        return toDTO(r);
    }

    public void eliminar(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationResponseDTO toDTO(Reservation r) {
        return new ReservationResponseDTO(
                r.getId(),
                r.getUsuarioId(),
                r.getInstalacionId(),
                r.getFecha(),
                r.getHoraInicio(),
                r.getHoraFin(),
                r.getPrecioTotal(),
                r.getEstado()
        );
    }
}
