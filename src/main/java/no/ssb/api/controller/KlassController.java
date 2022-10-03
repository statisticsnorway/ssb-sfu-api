package no.ssb.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.ssb.api.services.KlassKopiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by rsa on 26.10.2016.
 */
@Api("ssb-sfu-api")
@RestController
@RequestMapping("")
public class KlassController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KlassKopiService klassKopiService;


    @ApiOperation(value = "Oppdatere SN2007 i dsbbase fra Klass")
    @RequestMapping(value = "/oppdaterSN2007", method = RequestMethod.GET)
    public ResponseEntity<?> oppdaterSN2007(@RequestHeader HttpHeaders header) {
        String resultat = klassKopiService.oppdaterSN2007();
        return new ResponseEntity<>(resultat, resultat.equals(klassKopiService.altOk) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }






}
