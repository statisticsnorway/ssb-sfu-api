package no.ssb.api.database;



import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VwDlrEnhetId generated by hbm2java
 */
@Embeddable
public class VwDlrEnhetId implements java.io.Serializable {

    private static final long serialVersionUID = 5465537537379211224L;

    @Column(name = "DELREG_NR", nullable = false, precision = 22, scale = 0)
    private int delregNr;
    @Column(name = "IDENT_NR", nullable = false, length = 30)
    private String identNr;
    @Column(name = "ENHETS_TYPE", nullable = false, length = 4)
    private String enhetsType;

    public VwDlrEnhetId() {
    }

    public VwDlrEnhetId(int delregNr, String identNr, String enhetsType) {
        this.delregNr = delregNr;
        this.enhetsType = enhetsType;
        this.identNr = identNr;
    }

    public int getDelregNr() {
        return this.delregNr;
    }

    public void setDelregNr(int delregNr) {
        this.delregNr = delregNr;
    }

    public String getIdentNr() {
        return this.identNr;
    }

    public void setIdentNr(String identNr) {
        this.identNr = identNr;
    }

    public String getEnhetsType() {
        return this.enhetsType;
    }

    public void setEnhetsType(String enhetsType) {
        this.enhetsType = enhetsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VwDlrEnhetId)) return false;

        VwDlrEnhetId that = (VwDlrEnhetId) o;

        if (delregNr != that.delregNr) return false;
        if (identNr != null ? !identNr.equals(that.identNr) : that.identNr != null) return false;
        return enhetsType != null ? enhetsType.equals(that.enhetsType) : that.enhetsType == null;
    }

    @Override
    public int hashCode() {
        int result = delregNr;
        result = 31 * result + (identNr != null ? identNr.hashCode() : 0);
        result = 31 * result + (enhetsType != null ? enhetsType.hashCode() : 0);
        return result;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "VwDlrEnhetId [delregNr=" + delregNr + ", enhetsType=" + enhetsType
                + ", identNr=" + identNr +"]";
    }



    }
