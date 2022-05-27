package kz.ne.railways.tezcustoms.service.repository.asudkr;

import kz.ne.railways.tezcustoms.service.entity.asudkr.NeInvoice;
import kz.ne.railways.tezcustoms.service.model.UserInvoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NeInvoiceRepository extends JpaRepository<NeInvoice, Long> {

    @Query("select ni.invcNum as invoiceNum, ni.reciveStationCode, ni.destStationCode, ni.invcStatus, ni.uinpCode " +
            " from NeInvoice ni WHERE ni.userId = ?1")
    List<UserInvoices> getInvoicesByUser(Long userId);

}
