package no.ssb.api.util;

/**
 * Created by rsa on 19.09.2016.
 */
public class TypeKonverterer {

    public static String utsendingsTypeFraDigikorrTilSfu(String digikorrType) {
        String sfuType = "";
        switch (digikorrType) {
            case ("VEDOPL") : sfuType ="1"; break;
            case ("HOVED") : sfuType ="2"; break;
            case ("VEDMUL") : sfuType ="5"; break;
            case ("PAAMIN") : sfuType ="6"; break;
            case ("PURR") : sfuType ="7"; break;
            case ("INFO") : sfuType ="8"; break;
            case ("ANNEN") : sfuType ="9"; break;
            default: sfuType = digikorrType;
        }
        return sfuType;
    }


    public static String kommformTypeFraDigikorrTilSfu(String digikorrType) {
        String sfuType = "";
        switch (digikorrType) {
            case ("POST") : sfuType ="1"; break;
            case ("IDUN") : sfuType ="2"; break;
            case ("ALTINN") : sfuType ="3"; break;
            case ("KOSTRA") : sfuType ="4"; break;
            case ("TELEFON") : sfuType ="5"; break;
            case ("EPOST") : sfuType ="6"; break;
            case ("SMS") : sfuType ="7"; break;
            default: sfuType = digikorrType;
        }
        return sfuType;
    }
}
