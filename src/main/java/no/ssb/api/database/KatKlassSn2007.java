package no.ssb.api.database;

import no.ssb.api.pojo.ClassificationItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by rsa on 26.10.2016.
 */
@Entity
@Table(name = "KAT_KLASS_SN2007")
public class KatKlassSn2007 {

    @Id
    @Column(name = "KODE")
    private String kode;

    @Column(name = "NIVAA")
    private int nivaa;

    @Column(name = "NAVN")
    private String navn;

    @Column(name = "KORTNAVN")
    private String kortnavn;

    @Column(name = "TEKST")
    private String tekst;

    @Column(name = "FORELDER")
    private String forelder;

    @Column(name = "UTG")
    private int utg;

    public KatKlassSn2007() {
        this.utg = 0;
    }

    public KatKlassSn2007(String kode, int nivaa, String navn) {
        this.kode = kode;
        this.nivaa = nivaa;
        this.navn = navn;
        this.utg = 0;
    }

    public KatKlassSn2007(String kode, int nivaa, String navn, String kortnavn, String tekst, String forelder) {
        this.kode = kode;
        this.nivaa = nivaa;
        this.navn = navn;
        this.kortnavn = kortnavn;
        this.tekst = tekst;
        this.forelder = forelder;
        this.utg = 0;
    }

    public KatKlassSn2007(ClassificationItem classificationItem) {
        this.kode = classificationItem.getCode();
        this.nivaa = classificationItem.getLevel();
        this.navn = classificationItem.getName();
        this.kortnavn = classificationItem.getShortName();
        this.tekst = classificationItem.getNotes();
        this.forelder = classificationItem.getParentCode();
        this.utg = classificationItem.getUtg();
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getNivaa() {
        return nivaa;
    }

    public void setNivaa(int nivaa) {
        this.nivaa = nivaa;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getKortnavn() {
        return kortnavn;
    }

    public void setKortnavn(String kortnavn) {
        this.kortnavn = kortnavn;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getForelder() {
        return forelder;
    }

    public void setForelder(String forelder) {
        this.forelder = forelder;
    }

    public int getUtg() { return utg; }

    public void setUtg(int utg) { this.utg = utg; }

    @Override
    public String toString() {
        return "KatKlassSn2007{" +
                "kode='" + kode + '\'' +
                ", nivaa=" + nivaa +
                ", navn='" + navn + '\'' +
                ", kortnavn='" + kortnavn + '\'' +
                ", tekst='" + tekst + '\'' +
                ", forelder='" + forelder + '\'' +
                ", utg=" + utg +
                '}';
    }
}
