package no.ssb.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.ssb.api.pojo.Enhet;
import no.ssb.api.pojo.Utsending;
import no.ssb.api.services.UtsendingSfuService;
import no.ssb.api.util.TypeSkjemaEnheter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by rsa on 08.06.2016.
 */
@Api("ssb-sfu-api")
@RestController
@RequestMapping("")
public class UtsendingController {
    private static final String HEADER_APIKEY = "X-SSB-APIKEY";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${api-keys}")
    private List<String> acceptedApiKeys;

    @Autowired
    UtsendingSfuService utsendingSfuService;

    @ApiOperation(value = "Merke utvalg i SFU")
    @RequestMapping(value ="/merkUtsending", method = RequestMethod.POST)
    public ResponseEntity<?> genererBatch(@RequestBody String dataInn, @RequestHeader HttpHeaders header) {

        Utsending utsending = new Utsending();
        if (!authorizeRequest(header)) {
            return new ResponseEntity<>("Unauthorized. Api-key er ugyldig eller mangler", HttpStatus.UNAUTHORIZED);
        }
        try {
            logger.debug("dataInn: " + dataInn);
            utsending = createUtsendingFromInndata(dataInn);
            logger.debug("utsending: " + utsending.toString());

            if (utsending != null) {
                if (utsending.getUtsendingNr() == null || utsending.getUtsendingNr() <= 0) {
                    utsending.setUtsendingNr(utsendingSfuService.hentUtsendingsNr(utsending));
                }
                logger.debug("utsendingsnr: " + utsending.getUtsendingNr());
                if (utsending.getUtsendingNr() != null) {
                    ArrayList<TypeSkjemaEnheter> typeSkjemaEnheter = lagSkjemaEnhetListe(utsending.getEnheter());
                    for (TypeSkjemaEnheter tse : typeSkjemaEnheter) {
                        logger.info("typeSkjemaEnhet: {}, {}, {}", utsending.getDelregNr(), utsending.getUtsendingNr(), tse.toString());
                        utsendingSfuService.oppdaterEnhetPurretFlere(utsending.getDelregNr(), utsending.getUtsendingNr(), tse.getEnheter(), tse.getEnhetType(), tse.getSkjemaType());
                    }
                    utsendingSfuService.oppdaterPurret(utsending.getDelregNr(),  utsending.getUtsendingNr());
                } else {
                    return new ResponseEntity<Object>("Feilet (01)", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<Object>("Feilet (02)", HttpStatus.BAD_REQUEST);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("Feilet (03)", HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("Feilet (04)", HttpStatus.BAD_REQUEST);
        }
        logger.debug(utsending.toString());
        return new ResponseEntity<>("utsending behandlet: "+ utsending.toString(), HttpStatus.OK);

    }

    private Utsending createUtsendingFromInndata(@RequestBody String dataInn) throws JAXBException {
        Utsending utsending;
        JAXBContext jaxbContext = JAXBContext.newInstance(Utsending.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(dataInn);
        utsending = (Utsending) unmarshaller.unmarshal(reader);
        reader.close();
        utsending.konverterTypeFraDigikorrTilSfu();
        return utsending;
    }

    private ArrayList<TypeSkjemaEnheter> lagSkjemaEnhetListe(List<Enhet> enheter) {
        ArrayList<TypeSkjemaEnheter> typeSkjemaEnheterListe = new ArrayList<>();
        if (enheter != null) {
            enheter.forEach(enhet -> {
                logger.debug("   " + enhet.toString());
                AtomicBoolean typeSkjemaFunnet = new AtomicBoolean(false);
                typeSkjemaEnheterListe.forEach(tse -> {
                    if (tse.isRightTypeAndSkjema(enhet.getEnhetsType(), enhet.getSkjemaType())) {
                        tse.addEnhet(enhet.getIdentNr());
                        typeSkjemaFunnet.set(true);
                    }
                });
                if (!typeSkjemaFunnet.get()) {
                    typeSkjemaEnheterListe.add(new TypeSkjemaEnheter(enhet.getEnhetsType(), enhet.getSkjemaType(), enhet.getIdentNr()));
                }
            });
        }
        return typeSkjemaEnheterListe;
    }


    private boolean authorizeRequest(HttpHeaders header) {
        if (header.get(HEADER_APIKEY) == null) {
            return false;
        }
        return acceptedApiKeys.contains(header.get(HEADER_APIKEY).get(0));
    }


}