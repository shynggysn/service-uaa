package kz.ne.railways.tezcustoms.service.service.impl;

import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.service.EcpService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.util.Store;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collection;

@Slf4j
@Service
public class EcpServiceImpl implements EcpService {

    @Override
    public boolean isValidSigner(String signedData, User user) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(signedData);
            CMSSignedData signData = new CMSSignedData(decodedBytes);
            Store<X509CertificateHolder> certStore = signData.getCertificates();
            Collection<X509CertificateHolder> allStore = certStore.getMatches(null);
            String signerIIN = null;

            for (X509CertificateHolder certHolder : allStore) {
                X509Certificate x509Certificate = new JcaX509CertificateConverter().getCertificate(certHolder);

                X500Name x500Name = new X500Name(x509Certificate.getSubjectX500Principal().getName());
                RDN[] x500NameRDNs = x500Name.getRDNs(BCStyle.SERIALNUMBER);

                if (x500NameRDNs.length > 0) {
                    RDN iin = x500NameRDNs[0];
                    if (iin != null && iin.getFirst() != null) {
                        signerIIN = iin.getFirst().getValue().toString();
                    }
                }
            }

            if (signerIIN != null && signerIIN.substring(3, 15).equals(user.getIinBin())) {
                return true;
            } else {
                return false;
            }

        } catch (Exception exception) {
            log.error("Error while verifying ecp signer " + exception.getMessage());
            return false;
        }

    }

}
