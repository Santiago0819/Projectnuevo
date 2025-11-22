package com.centrodeportivo.reservas;

import com.centrodeportivo.reservas.model.Invoice;
import com.centrodeportivo.reservas.model.Installation;
import com.centrodeportivo.reservas.model.Reservation;
import com.centrodeportivo.reservas.model.Socio;
import com.centrodeportivo.reservas.model.enums.ReserveStatus;
import com.centrodeportivo.reservas.model.enums.InstallationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationServiceTest {

    @Test
    void testSolapamientoReservas() {
        Reservation r1 = new Reservation();
        r1.setInstalacionId(1L);
        r1.setFecha(LocalDate.of(2025, 1, 1));
        r1.setHoraInicio(LocalTime.of(10, 0));
        r1.setHoraFin(LocalTime.of(11, 0));
        r1.setEstado(ReserveStatus.CONFIRMADA);

        Reservation r2 = new Reservation();
        r2.setInstalacionId(1L);
        r2.setFecha(LocalDate.of(2025, 1, 1));
        r2.setHoraInicio(LocalTime.of(10, 30));
        r2.setHoraFin(LocalTime.of(11, 30));

        Assertions.assertTrue(r1.seSolapaCon(r2));
    }

    @Test
    void testNoSolapamientoInstalacionDistinta() {
        Reservation r1 = new Reservation();
        r1.setInstalacionId(1L);
        r1.setFecha(LocalDate.now());
        r1.setHoraInicio(LocalTime.of(10, 0));
        r1.setHoraFin(LocalTime.of(11, 0));

        Reservation r2 = new Reservation();
        r2.setInstalacionId(2L);
        r2.setFecha(r1.getFecha());
        r2.setHoraInicio(LocalTime.of(10, 30));
        r2.setHoraFin(LocalTime.of(11, 30));

        Assertions.assertFalse(r1.seSolapaCon(r2));
    }

    @Test
    void testDescuentoSocioGold() {
        Socio socio = new Socio();
        socio.setNivelMembresia("GOLD");
        double base = 100.0;
        double descuento = socio.calcularDescuento(base);
        Assertions.assertEquals(20.0, descuento);
    }

    @Test
    void testDescuentoSocioSilver() {
        Socio socio = new Socio();
        socio.setNivelMembresia("SILVER");
        double base = 100.0;
        double descuento = socio.calcularDescuento(base);
        Assertions.assertEquals(10.0, descuento);
    }

    @Test
    void testDescuentoSocioSinMembresia() {
        Socio socio = new Socio();
        socio.setNivelMembresia("BRONZE");
        double base = 100.0;
        double descuento = socio.calcularDescuento(base);
        Assertions.assertEquals(0.0, descuento);
    }

    @Test
    void testInstalacionTarifaHora() {
        Installation inst = new Installation();
        inst.setTarifaHora(50.0);
        Assertions.assertEquals(50.0, inst.getTarifaHora());
    }

    @Test
    void testFacturaCalcularTotal() {
        Invoice f = new Invoice();
        f.setSubtotal(200.0);
        f.setDescuento(50.0);
        Assertions.assertEquals(150.0, f.calcularTotal());
    }

    @Test
    void testTipoInstalacionEnum() {
        InstallationType tipo = InstallationType.CANCHA_FUTBOL;
        Assertions.assertEquals("CANCHA_FUTBOL", tipo.name());
    }
}
