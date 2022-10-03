package no.ssb.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.ssb.api.database.VwDlrEnhet;
import no.ssb.api.services.EnhetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * Created by rsa on 08.06.2016.
 */
@Api("ssb-sfu-api")
@RestController
@RequestMapping("")
public class UtvalgController {
    private static final String HEADER_APIKEY = "X-SSB-APIKEY";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${api-keys}")
    private List<String> acceptedApiKeys;

    @Autowired
    EnhetService enhetService;

    @ApiOperation(value = "Ping metode for å kunne sjekke at API'et er oppe")
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<?> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

    @ApiOperation(value = "Hente antall i utvalg")
    @RequestMapping(value = "/hentAntallUtvalg/{delregnr}", method = RequestMethod.GET)
    public ResponseEntity<?> hentAntallUtvalg(@PathVariable int delregnr, @RequestHeader HttpHeaders header) throws IOException {
        if (!authorizeRequest(header)) {
            return new ResponseEntity<>("Unauthorized. Api-key er ugyldig eller mangler", HttpStatus.UNAUTHORIZED);
        }
        logger.info("Delregnr: {}", delregnr);
        List<VwDlrEnhet> vwDlrEnheter = enhetService.hentUtvalg(delregnr);
        return new ResponseEntity<>("Antall enheter hentet ut: " + vwDlrEnheter.size(), HttpStatus.OK);
    }

    @ApiOperation(value = "Hente utvalgsliste")
    @RequestMapping(value="/hentUtvalgJson/{delregnr}",
            method=RequestMethod.GET,
            produces="application/json")
    public ResponseEntity<?> getUtvalgXml(@PathVariable int delregnr, @RequestHeader HttpHeaders header)
    {
        if (!authorizeRequest(header)) {
            return new ResponseEntity<>("Unauthorized. Api-key er ugyldig eller mangler", HttpStatus.UNAUTHORIZED);
        }
        logger.info("Henter utvalg for delregnr: {}", delregnr);
        List<VwDlrEnhet> vwDlrEnheter = enhetService.hentUtvalg(delregnr);
        return new ResponseEntity<>(vwDlrEnheter, HttpStatus.OK);
    }


    private boolean authorizeRequest(HttpHeaders header) {
        if (header.get(HEADER_APIKEY) == null) {
            return false;
        }
        return acceptedApiKeys.contains(header.get(HEADER_APIKEY).get(0));
    }


}