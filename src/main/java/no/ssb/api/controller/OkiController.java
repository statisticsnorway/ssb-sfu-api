package no.ssb.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.ssb.api.pojo.Enhet;
import no.ssb.api.pojo.OkiEnhet;
import no.ssb.api.pojo.OkiEnheter;
import no.ssb.api.services.OkiPuljeStatusService;
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
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rsa on 08.06.2016.
 */
@Api("ssb-sfu-api")
@RestController
@RequestMapping("")
public class OkiController {
    private static final String HEADER_APIKEY = "X-SSB-APIKEY";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${api-keys}")
    private List<String> acceptedApiKeys;

    @Autowired
    OkiPuljeStatusService okiPuljeStatusService;



    @ApiOperation(value = "Hente neste usendte skjema-puljenr")
    @RequestMapping(value = "/hentNesteUsendtSkjemapulje/{delregnr}", method = RequestMethod.GET)
    public ResponseEntity<?> hentNesteUsendtSkjemapulje(@PathVariable int delregnr, @RequestHeader HttpHeaders header) throws IOException {
        if (!authorizeRequest(header)) {
            return new ResponseEntity<>("Unauthorized. Api-key er ugyldig eller mangler", HttpStatus.UNAUTHORIZED);
        }
        logger.info("Delregnr: {}", delregnr);
        Long puljeNr = okiPuljeStatusService.hentNesteUsendtSkjemaPulje(new Long(delregnr));
        return new ResponseEntity<>("Neste skjemapulje: " + puljeNr, HttpStatus.OK);
    }




    @ApiOperation(value = "Sett puljenr og returner kan-oppdateres-status")
    @RequestMapping(value="/settPuljeHentStatus", method = RequestMethod.POST)
    public ResponseEntity<?> settPuljeHentStatus(@RequestBody String dataInn, @RequestHeader HttpHeaders header) {

        String enheterStatus = "";
        OkiEnheter okiEnheter;
        if (!authorizeRequest(header)) {
            return new ResponseEntity<>("Unauthorized. Api-key er ugyldig eller mangler", HttpStatus.UNAUTHORIZED);
        }
        try {
            logger.debug("dataInn: " + dataInn);
            JAXBContext jaxbContext = JAXBContext.newInstance(OkiEnheter.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(dataInn);
            okiEnheter = (OkiEnheter) unmarshaller.unmarshal(reader);
            reader.close();
            logger.debug("delregNr: " + okiEnheter.getDelregNr() + ", okiEnheter: " + okiEnheter.getOkiEnhetListe().size());


            if (okiEnheter != null && okiEnheter.getOkiEnhetListe() != null && okiEnheter.getOkiEnhetListe().size() > 0) {
//                Long puljeNr = okiPuljeStatusService.hentNesteUsendtSkjemaPulje(okiEnheter.getDelregNr());
//                logger.debug("puljeNr: " + puljeNr);
//                if (puljeNr > -1) {
                    StringBuffer hVar31Enheter = new StringBuffer();
                    StringBuffer hVar32Enheter = new StringBuffer();
                    for (OkiEnhet okiEnhet : okiEnheter.getOkiEnhetListe()) {
                        if (okiEnhet.getOrgnr() != null && okiEnhet.getOrgnr().length() > 0) {
                            logger.debug("   " + okiEnhet.toString());
                            if ("1".equals(okiEnhet.getHVar3())) {
                                hVar31Enheter.append(okiEnhet.getOrgnr()).append(";");
                            } else {
                                hVar32Enheter.append(okiEnhet.getOrgnr()).append(";");
                            }
                        }
                    }
                    logger.debug("hVar31Enheter: " + hVar31Enheter);
                    logger.debug("hvar32Enheter: " + hVar32Enheter);
                    enheterStatus = okiPuljeStatusService.settPuljeHentStatusFlere(okiEnheter.getDelregNr(), hVar31Enheter.toString(), 1L, "OkiControl");
                    logger.debug("enheter med status (1): " + enheterStatus);
                    enheterStatus = enheterStatus + okiPuljeStatusService.settPuljeHentStatusFlere(okiEnheter.getDelregNr(), hVar32Enheter.toString(), 2L, "OkiControl");
                    logger.debug("enheter med status (1 og 2): " + enheterStatus);
                    if (enheterStatus != null && enheterStatus.length() > 1) {
//                        enheterStatus = "{\"enheter\":[" + enheterStatus + "]}";
                        enheterStatus = "[" + enheterStatus.substring(0,enheterStatus.length()-1) + "]";
                    }
//                } else {
//                    return new ResponseEntity<Object>("Feilet (01) - ikke nytt puljenr", HttpStatus.BAD_REQUEST);
//                }
            } else {
                return new ResponseEntity<Object>("Feilet (02) - ingen enheter til behandling", HttpStatus.BAD_REQUEST);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("Feilet (03) - jaxbexception:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("Feilet (04) - exception", HttpStatus.BAD_REQUEST);
        }
        logger.debug("enheter med status: " + (enheterStatus.length() > 1000 ? enheterStatus.substring(0,1000) : enheterStatus));
        return new ResponseEntity<>(enheterStatus, HttpStatus.OK);

    }


    private boolean authorizeRequest(HttpHeaders header) {
        if (header.get(HEADER_APIKEY) == null) {
            return false;
        }
        return acceptedApiKeys.contains(header.get(HEADER_APIKEY).get(0));
    }



}