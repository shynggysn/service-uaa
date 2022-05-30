package kz.ne.railways.tezcustoms.service.repository.asudkr;

import kz.ne.railways.tezcustoms.service.entity.asudkr.NeInvoice;
import kz.ne.railways.tezcustoms.service.model.UserInvoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NeInvoiceRepository extends JpaRepository<NeInvoice, Long> {

    @Query("select ni.invcNum as invoiceNum, CONCAT(ni.reciveStationCode, ' ', sr.staName) as reciveStationCode, " +
            "CONCAT(ni.destStationCode, ' ', sd.staName) as destStationCode, ni.invcDt as invoiceDate, " +
            "ni.invoiceStatus as invoiceStatus, ni.uinpCode as uinpCode from NeInvoice ni " +
            "left join Sta sd ON ni.destStationCode = sd.staNo  and sd.stEnd > CURRENT_TIMESTAMP " +
            "left join Sta sr ON ni.reciveStationCode = sr.staNo and sr.stEnd > CURRENT_TIMESTAMP " +
            "WHERE ni.userId = ?1")
    List<UserInvoices> getInvoicesByUser(Long userId);

}
