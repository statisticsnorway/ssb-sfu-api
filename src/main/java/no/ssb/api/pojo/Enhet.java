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
public class Enhet {
    @XmlElement
    private String identNr;
    @XmlElement
    private String enhetsType;
    @XmlElement
    private String skjemaType;

    public String getEnhetsType() {
        return enhetsType;
    }

    public void setEnhetsType(String enhetsType) {
        this.enhetsType = enhetsType;
    }

    public String getIdentNr() {
        return identNr;
    }

    public void setIdentNr(String identNr) {
        this.identNr = identNr;
    }

    public String getSkjemaType() { return skjemaType; }

    public void setSkjemaType(String skjemaType) { this.skjemaType = skjemaType; }

    @Override
    public String toString() {
        return "Enhet{" +
                "identNr='" + identNr + '\'' +
                ", enhetsType='" + enhetsType + '\'' +
                ", skjemaType='" + skjemaType + '\'' +
                '}';
    }
}
