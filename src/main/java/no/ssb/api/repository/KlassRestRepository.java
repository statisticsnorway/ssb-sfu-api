package no.ssb.api.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rsa on 20.03.2017.
 */
@Component
public class KlassRestRepository implements KlassRepository {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${klassSN2007Url}")
    String klassUrl;

    @Override
    public String hentKlassXml() throws Exception {
        String restKlassURL = klassUrl;
        log.info("klass-url er: " + restKlassURL);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> klassResponse = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(requestHeaders);
        try {
            klassResponse = restTemplate.exchange(restKlassURL, HttpMethod.GET, httpEntity, String.class);
            log.info("statuskode: " + klassResponse.getStatusCode().toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return (klassResponse.getStatusCode() != null && klassResponse.getStatusCode().value() == 200) ?
                klassResponse.getBody() : String.valueOf(klassResponse.getStatusCode());
    }



}


