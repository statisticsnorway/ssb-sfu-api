package no.ssb.api.repository;

import no.ssb.api.database.DlrPurredato;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by rsa on 19.09.2016.
 */
@Repository
public interface UtsendingRepository extends CrudRepository<DlrPurredato, Long> {

    @Procedure(name="finnUtsendingsNr")
    Long finnUtsendingsNr(
            @Param("p_delreg_nr") Long delregNr
          , @Param("p_utsend_type") String utsendingsType
          , @Param("p_komm_form") String kommunikasjonsform
          , @Param("p_pulje_nr") Long puljeNr
    );

    @Procedure(name="oppdaterUtsending")
    void oppdaterUtsending(
            @Param("p_delreg_nr") Long delregNr
            , @Param("p_purre_nr") Long utsendingsNr
            , @Param("p_dato") Timestamp datoTid
            , @Param("p_bruker") String bruker
    );

    @Procedure(name="oppdaterUtsendingEnhet")
    void oppdaterUtsendingEnhet(
            @Param("p_delreg_nr") Long delregNr
            , @Param("p_purre_nr") Long utsendingsNr
            , @Param("p_ident_nr") String identNr
            , @Param("p_enhets_type") String enhetsType
            , @Param("p_skjema_type") String skjemaType
            , @Param("p_bruker") String bruker
    );

    @Procedure(name="oppdaterUtsendingEnheter")
    void oppdaterUtsendingEnheter(
            @Param("p_delreg_nr") Long delregNr
            , @Param("p_purre_nr") Long utsendingsNr
            , @Param("p_ident_nr") String identNr
            , @Param("p_enhets_type") String enhetsType
            , @Param("p_skjema_type") String skjemaType
            , @Param("p_bruker") String bruker
    );

}
