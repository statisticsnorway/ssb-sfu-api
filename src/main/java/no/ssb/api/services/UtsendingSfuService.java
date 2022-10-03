package no.ssb.api.services;

import no.ssb.api.pojo.Utsending;
import no.ssb.api.repository.UtsendingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by rsa on 19.09.2016.
 */
@Service
@Transactional
public class UtsendingSfuService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtsendingRepository utsendingRepository;

    public Long hentUtsendingsNr(Utsending utsending) {

        Long utsendingsNr = utsendingRepository.finnUtsendingsNr(
                utsending.getDelregNr()
                , utsending.getUtsendingsType()
                , utsending.getKommunikasjonsform()
                , utsending.getPuljeNr() != null ? utsending.getPuljeNr() : -1L
        );
        System.out.println("utsendingsnummer: " + utsendingsNr);
        return utsendingsNr;
    }


     public void oppdaterPurret(Long delregNr, Long utsendingsNr) {
         utsendingRepository.oppdaterUtsending(delregNr, utsendingsNr, new Timestamp(System.currentTimeMillis()), "digikorr");
     }

    public void oppdaterEnhetPurret(Long delregNr, Long utsendingsNr, String identNr, String enhetsType) {
        utsendingRepository.oppdaterUtsendingEnhet(delregNr, utsendingsNr, identNr, enhetsType, "", "digikorr");
    }

    public void oppdaterEnhetPurretFlere(Long delregNr, Long utsendingsNr, String identNumre, String enhetsType, String skjemaType) throws SQLException {
        logger.info("Oppdater purretabell for {}, {}, {}", delregNr, utsendingsNr, identNumre, enhetsType, skjemaType);
        utsendingRepository.oppdaterUtsendingEnheter(delregNr, utsendingsNr, identNumre, enhetsType, skjemaType, "digikorr");
    }
}
