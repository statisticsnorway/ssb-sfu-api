package no.ssb.api.pojo;

import no.ssb.api.util.TypeKonverterer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by rsa on 16.09.2016.
 */
@XmlRootElement(name="utsending")
@XmlAccessorType(XmlAccessType.FIELD)
public class Utsending {
    @XmlElement
    private Long delregNr;
    @XmlElement
    private String utsendingsType;
    @XmlElement
    private String kommunikasjonsform;
    @XmlElement
    private Long puljeNr;
    @XmlElement
    private Long utsendingNr;
    @XmlElement(name="enhet")
    private List<Enhet> enheter = null;

    public Long getDelregNr() {
        return delregNr;
    }

    public void setDelregNr(Long delregNr) {
        this.delregNr = delregNr;
    }

    public String getUtsendingsType() {
        return utsendingsType;
    }

    public void setUtsendingsType(String utsendingsType) {
        this.utsendingsType = utsendingsType;
    }

    public String getKommunikasjonsform() {
        return kommunikasjonsform;
    }

    public void setKommunikasjonsform(String kommunikasjonsform) {
        this.kommunikasjonsform = kommunikasjonsform;
    }

    public Long getPuljeNr() {
        return puljeNr;
    }

    public void setPuljeNr(Long puljeNr) {
        this.puljeNr = puljeNr;
    }

    public Long getUtsendingNr() {
        return utsendingNr;
    }

    public void setUtsendingNr(Long utsendingNr) {
        this.utsendingNr = utsendingNr;
    }

    public List<Enhet> getEnheter() {
        return enheter;
    }

    public void setEnheter(List<Enhet> enheter) {
        this.enheter = enheter;
    }

    public void konverterTypeFraDigikorrTilSfu() {
        this.utsendingsType = TypeKonverterer.utsendingsTypeFraDigikorrTilSfu(this.utsendingsType);
        this.kommunikasjonsform = TypeKonverterer.kommformTypeFraDigikorrTilSfu(this.kommunikasjonsform);
    }

    @Override
    public String toString() {
        return "Utsending{" +
                "delregNr='" + delregNr + '\'' +
                ", utsendingsType='" + utsendingsType + '\'' +
                ", kommunikasjonsform='" + kommunikasjonsform + '\'' +
                ", puljeNr=" + puljeNr +
                ", utsendingNr=" + utsendingNr +
                ", enheter=" + (enheter != null ? enheter.size() : "ingen enheter") +
                "}";
    }
}
