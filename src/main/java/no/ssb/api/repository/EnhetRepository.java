package no.ssb.api.repository;

import no.ssb.api.database.VwDlrEnhet;
import no.ssb.api.database.VwDlrEnhetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mnm on 06.09.2016.
 */
@Repository
public interface EnhetRepository extends JpaRepository<VwDlrEnhet, VwDlrEnhetId> {

    List<VwDlrEnhet> findByIdDelregNr(int delregNr);
}

