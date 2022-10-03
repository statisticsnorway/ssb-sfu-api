package no.ssb.api.services;

import no.ssb.api.database.KatKlassSn2007;
import no.ssb.api.pojo.ClassificationVersion;
import no.ssb.api.pojo.Utsending;
import no.ssb.api.repository.KlassKopiRepository;
import no.ssb.api.repository.KlassRepository;
import no.ssb.api.repository.UtsendingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by rsa on 19.09.2016.
 */

@Service
@Transactional
public class KlassKopiService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    final public static String altOk = "klassKopi for sn2007 ok";
    final public static String ingenClassificationVersion = "noe har feilet i oppdatering sv sn2007 - ingen classificationVersion";
    final public static String xmlIkkeOk = "noe har feilet i oppdatering sv sn2007 - xml fra Klass-kall";

    @Autowired
    KlassRepository klassRepository;


    @Autowired
    KlassKopiRepository klassKopiRepository;

    /**
     * Henter sn2007-xml fra klass-api, genererer classificationVersion fra xml,
     * henter alle classificationItems fra classificationVersion og lagrer disse til databasen.
     *
     * @return String med resultat fra oppdatering
     */
    public String oppdaterSN2007() {
        String klassXml = null;
        try {
            klassXml = klassRepository.hentKlassXml();
            log.debug("klassXml: " + klassXml);
            ClassificationVersion classificationVersion = null;

            if (klassXml != null && klassXml.length() > 20) {
                classificationVersion = genererClassificationVersion(klassXml);
                if (classificationVersion != null) {
                    klassKopiRepository.setKatKlassSn2007UtgTil1();
                    List<KatKlassSn2007> klassSn2007Items = classificationVersion.getClassificationItems().getClassificationItems().stream().map(KatKlassSn2007::new).collect(Collectors.toList());
                    klassKopiRepository.save(klassSn2007Items);
                } else {
                    log.error(ingenClassificationVersion);
                    return ingenClassificationVersion;
                }
            } else {
                log.error(xmlIkkeOk + "/n/t" + klassXml);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return altOk;
    }


    /**
     * Bruker JAXB til å generere classificationVersion-objekt fra xml.
     *
     * @param klassXml String med SN2007-klassifikasjoner fra Klass
     * @return ClassificationVersion-objekt
     */
    private ClassificationVersion genererClassificationVersion(String klassXml) {
        ClassificationVersion classificationVersion = null;
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(ClassificationVersion.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(klassXml);
            classificationVersion = (ClassificationVersion) unmarshaller.unmarshal(reader);
            reader.close();
            log.debug("classificationItems: " + classificationVersion.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return classificationVersion;
    }





}
