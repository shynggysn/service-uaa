package kz.ne.railways.tezcustoms.service.mapper;

import kz.ne.railways.tezcustoms.service.entity.asudkr.NeInvoice;

public class NeInvoiceMapper {

    public static NeInvoice fromId(Long id) {
        NeInvoice neInvoice = new NeInvoice();
        neInvoice.setInvcUn(id);
        return neInvoice;
    }
}
