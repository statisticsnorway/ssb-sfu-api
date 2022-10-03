package no.ssb.api.util;

import no.ssb.api.pojo.Enhet;

public class TypeSkjemaEnheter {
    private String enhetType;
    private String skjemaType;
    private String enheter;

    public TypeSkjemaEnheter(String enhetType, String skjemaType, String enheter) {
        this.enhetType = enhetType;
        this.skjemaType = skjemaType;
        this.enheter = enheter;
    }

    public String getEnhetType() {
        return enhetType;
    }

    public String getSkjemaType() {
        return skjemaType;
    }

    public String getEnheter() {
        return enheter;
    }

    public void addEnhet(String enhet) {
        this.enheter = (this.enheter.isEmpty() ? "" : this.enheter + ";") + enhet;
    }

    public boolean isRightTypeAndSkjema(String enhetType, String skjemaType) {
        return this.enhetType.equals(enhetType) &&
                ((this.skjemaType == null && skjemaType == null) || (this.skjemaType != null && this.skjemaType.equals(skjemaType)));
    }


    @Override
    public String toString() {
        return "TypeSkjemaEnheter{" +
                "enhetType=" + enhetType +
                ", skjemaType=" + skjemaType +
                ", enheter=" + enheter +
                '}';
    }
}
