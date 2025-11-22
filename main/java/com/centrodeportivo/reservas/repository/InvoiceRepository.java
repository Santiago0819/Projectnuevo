package com.centrodeportivo.reservas.repository;

import com.centrodeportivo.reservas.model.Invoice;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepository extends CsvRepository<Invoice> {

    public InvoiceRepository() {
        super("facturas.csv");
    }

    @Override
    protected String getHeaders() {
        return "id;reservaId;pagoId;subtotal;descuento;total";
    }

    @Override
    protected String toCsvRow(Invoice f) {
        return String.join(";",
                String.valueOf(f.getId()),
                String.valueOf(f.getReservaId()),
                String.valueOf(f.getPagoId()),
                String.valueOf(f.getSubtotal()),
                String.valueOf(f.getDescuento()),
                String.valueOf(f.getTotal())
        );
    }

    @Override
    protected Invoice fromCsvRow(String row) {
        String[] p = row.split(";");
        Invoice f = new Invoice();
        f.setId(Long.parseLong(p[0]));
        f.setReservaId(Long.parseLong(p[1]));
        f.setPagoId(Long.parseLong(p[2]));
        f.setSubtotal(Double.parseDouble(p[3]));
        f.setDescuento(Double.parseDouble(p[4]));
        f.setTotal(Double.parseDouble(p[5]));
        return f;
    }
}
