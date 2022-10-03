package no.ssb.api.services;

import no.ssb.api.database.VwDlrEnhet;
import no.ssb.api.repository.EnhetRepository;
import no.ssb.api.repository.OkiPuljeStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsa on 20.02.2017.
 */
@Service
@Transactional
public class OkiPuljeStatusService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
     @Autowired
     OkiPuljeStatusRepository okiPuljeStatusRepository;

    public Long hentNesteUsendtSkjemaPulje(Long delregNr){
        Long puljeNr = -1L;
        puljeNr = okiPuljeStatusRepository.hentNesteUsendtSkjemaPulje(delregNr);
        return puljeNr;
    }

    public String settPuljeHentStatus(Long delregNr, String orgnr, Long puljeNr, Long hVar3, String bruker) {
        String enhetPuljeStatus = "";
        enhetPuljeStatus = okiPuljeStatusRepository.settPuljeHentStatus(delregNr, orgnr, puljeNr, hVar3, bruker);
        return enhetPuljeStatus;
    }

    public String settPuljeHentStatusFlere(Long delregNr, String orgnumre, Long hVar3, String bruker) {
        String enhetPuljeStatus = "";
        if (orgnumre != null && orgnumre.length() > 0) {
            logger.info("orgnumre:" + orgnumre);
            enhetPuljeStatus = okiPuljeStatusRepository.settPuljeHentStatusFlere(delregNr, orgnumre, hVar3, bruker);
            logger.info("puljestatus: " + enhetPuljeStatus);
        }
        return enhetPuljeStatus == null ? "" : enhetPuljeStatus;
    }
}
