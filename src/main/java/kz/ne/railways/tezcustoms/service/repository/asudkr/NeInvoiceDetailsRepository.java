package kz.ne.railways.tezcustoms.service.repository.asudkr;

import kz.ne.railways.tezcustoms.service.entity.asudkr.NeInvoice;
import kz.ne.railways.tezcustoms.service.entity.asudkr.NeInvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeInvoiceDetailsRepository  extends JpaRepository<NeInvoiceDetails, Long> {

    Optional<NeInvoiceDetails> findByNeInvoice (NeInvoice neInvoice);

}
