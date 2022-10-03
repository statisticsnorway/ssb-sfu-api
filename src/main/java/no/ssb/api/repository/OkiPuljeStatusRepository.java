package no.ssb.api.repository;

import no.ssb.api.database.OkiPuljeStatus;
import no.ssb.api.database.VwDlrEnhet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.util.List;

/**
 * Created by rsa on 20.02.2017.
 */
@Repository
public interface OkiPuljeStatusRepository extends JpaRepository<OkiPuljeStatus, Long> {

    @Procedure(name="hentNesteUsendtSkjemaPulje")
    Long hentNesteUsendtSkjemaPulje(
            @Param("p_delreg_nr") Long delregNr
    );

    @Procedure(name="settPuljeHentStatus")
    String settPuljeHentStatus(
            @Param("p_delreg_nr") Long delregNr
            , @Param("p_orgnr") String orgnr
            , @Param("p_pulje_nr") Long puljeNr
            , @Param("p_hvar3") Long hvar3
            , @Param("p_bruker") String bruker
    );

    @Procedure(name="settPuljeHentStatusFlere")
    String settPuljeHentStatusFlere(
            @Param("p_delreg_nr") Long delregNr
            , @Param("p_orgnumre") String orgnumre
            , @Param("p_hvar3") Long hvar3
            , @Param("p_bruker") String bruker
    );
}

