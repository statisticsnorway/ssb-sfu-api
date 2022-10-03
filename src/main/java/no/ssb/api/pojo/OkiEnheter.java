package no.ssb.api.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsa on 20.02.2017.
 */
@XmlRootElement
public class OkiEnheter {
    private Long delregNr;

    private List<OkiEnhet> okiEnhetListe = new ArrayList<>();


    @XmlElement(name="delregNr")
    public Long getDelregNr() {
        return delregNr;
    }

    public void setDelregNr(Long delregNr) {
        this.delregNr = delregNr;
    }


    @XmlElementWrapper
    @XmlElement(name="okiEnhet")
    public List<OkiEnhet> getOkiEnhetListe() {
        return okiEnhetListe;
    }

    public void setOkiEnhetListe(List<OkiEnhet> okiEnhetListe) {
        this.okiEnhetListe = okiEnhetListe;
    }

    @Override
    public String toString() {
        String enheter = "";
        for( OkiEnhet okiEnhet : okiEnhetListe) {
            enheter = enheter + "\n\t" + okiEnhet.toString();
        }
        return "OkiEnheter{" +
                "delregNr=" + delregNr +
                ", okiEnhetListe=" + enheter +
                '}';
    }
}
