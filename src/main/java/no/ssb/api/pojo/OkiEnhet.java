package no.ssb.api.pojo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rsa on 16.09.2016.
 */
@XmlRootElement(name="enhet")
@XmlAccessorType(XmlAccessType.FIELD)
public class OkiEnhet {
    @XmlElement
    private Long delregNr;

    @XmlElement
    private String orgnr;

    @XmlElement
    private String hVar3;

    @XmlElement
    private String bruker;


    public String getOrgnr() {
        return orgnr;
    }

    public void setOrgnr(String orgnr) {
        this.orgnr = orgnr;
    }

    public Long getDelregNr() {
        return delregNr;
    }

    public void setDelregNr(Long delregNr) {
        this.delregNr = delregNr;
    }

    public String getHVar3() {
        return hVar3;
    }

    public void setHVar3(String hVar3) {
        this.hVar3 = hVar3;
    }

    public String getBruker() {
        return bruker;
    }

    public void setBruker(String bruker) {
        this.bruker = bruker;
    }

    @Override
    public String toString() {
        return "OkiEnhet{" +
                "delregNr=" + delregNr +
                ", orgnr='" + orgnr + '\'' +
                ", hVar3='" + hVar3 + '\'' +
                ", bruker='" + bruker + '\'' +
                '}';
    }
}
