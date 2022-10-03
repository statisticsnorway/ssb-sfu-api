package no.ssb.api.services;

import no.ssb.api.database.VwDlrEnhet;
import no.ssb.api.repository.EnhetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnm on 06.09.2016.
 */
@Service
@Transactional
public class EnhetService {
     @Autowired
     EnhetRepository enhetRepository;

    public List<VwDlrEnhet> hentUtvalg(Integer delregNr){
        List<VwDlrEnhet> vwDlrEnheter = new ArrayList<>();
        vwDlrEnheter = enhetRepository.findByIdDelregNr(delregNr);
        return vwDlrEnheter;
    }
}
