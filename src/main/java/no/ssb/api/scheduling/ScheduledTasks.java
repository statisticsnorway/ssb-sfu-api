package no.ssb.api.scheduling;

import no.ssb.api.services.KlassKopiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by rsa on 20.03.2017.
 */
@Service
public class ScheduledTasks {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KlassKopiService klassKopiService;


    @Scheduled(cron = "0 0 12 ? * MON-FRI")
//    @Scheduled(fixedRate = 500000)
    public void kopierKlassSn2007() {
        System.out.println("kopier klass");
        log.info("Kjører sjekk av varsel");
        klassKopiService.oppdaterSN2007();
    }
}
