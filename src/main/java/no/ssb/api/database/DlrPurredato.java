package no.ssb.api.database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by rsa on 19.09.2016.
 */
@Entity
@Table(name = "DLR_PURREDATO")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "finnUtsendingsNr",
                procedureName = "dlr_purret_pkg.hent_purre_nr",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_utsend_type", type = String.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_komm_form", type = String.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pulje_nr", type = Long.class)
                      , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_utsendings_nr", type = Long.class)
                }),


        @NamedStoredProcedureQuery(name = "oppdaterUtsending",
                procedureName = "dlr_purret_pkg.oppdater_purredato",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_purre_nr", type = Long.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dato", type = Timestamp.class)
                      , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bruker", type = String.class)
                }),

        @NamedStoredProcedureQuery(name = "oppdaterUtsendingEnhet",
        procedureName = "dlr_purret_pkg.oppdater_dsbbase_purret",
        parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_purre_nr", type = Long.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ident_nr", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_enhets_type", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_skjema_type", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bruker", type = String.class)
            }),

        @NamedStoredProcedureQuery(name = "oppdaterUtsendingEnheter",
        procedureName = "dlr_purret_pkg.oppdater_dsbbase_purret_flere",
        parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_purre_nr", type = Long.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ident_nr", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_enhets_type", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_skjema_type", type = String.class)
                    , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bruker", type = String.class)
            })
})

public class DlrPurredato implements Serializable {
    @Id
    Long delregNr;

}
