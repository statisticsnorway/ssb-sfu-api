package no.ssb.api.database;

import javax.persistence.*;

/**
 * Created by mnm on 06.09.2016.
 */
@Entity
@Table(name = "VW_DLR_ENHET" )
public class VwDlrEnhet {


    private VwDlrEnhetId id;

    @Column(name = "orgnr")
    private String orgnr;

    public VwDlrEnhet() {}

    public VwDlrEnhet(VwDlrEnhetId id, String orgnr) {
        this.id = id;
        this.orgnr = orgnr;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "delregNr", column = @Column(name = "DELREG_NR", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "enhetsType", column = @Column(name = "ENHETS_TYPE", nullable = false, length = 4)),
            @AttributeOverride(name = "enhetsId", column = @Column(name = "ENHETS_ID", nullable = false, length = 30)),
    })
    public VwDlrEnhetId getId() {
        return this.id;
    }

    public void setId(VwDlrEnhetId id) {
        this.id = id;
    }

    public String getOrgnr() {
        return orgnr;
    }

    public void setOrgnr(String orgnr) {
        this.orgnr = orgnr;
    }
}
