package no.ssb.api.database;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by rsa on 20.02.2017.
 */
    @Entity
    @Table(name = "DLR_ENHET_I_DELREG")
    @NamedStoredProcedureQueries({

            @NamedStoredProcedureQuery(name = "hentNesteUsendtSkjemaPulje",
                    procedureName = "dlr_oki_pulje_status_pkg.hent_neste_pulje",
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_pulje_nr", type = Long.class)
                    }),

            @NamedStoredProcedureQuery(name = "settPuljeHentStatus",
                    procedureName = "dlr_oki_pulje_status_pkg.oppdater_enhet_pulje_hvar3",
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgnr", type = String.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_puljenr", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hvar3", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bruker", type = String.class)
                            , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_status", type = String.class)
                    }),

            @NamedStoredProcedureQuery(name = "settPuljeHentStatusFlere",
                    procedureName = "dlr_oki_pulje_status_pkg.oppdater_enheter_pulje_hvar3",
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_delreg_nr", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgnumre", type = String.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hvar3", type = Long.class)
                            , @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bruker", type = String.class)
                            , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_returenheter", type = String.class)
                    })
    })

    public class OkiPuljeStatus implements Serializable {
        @Id
        Long delregNr;

    }


