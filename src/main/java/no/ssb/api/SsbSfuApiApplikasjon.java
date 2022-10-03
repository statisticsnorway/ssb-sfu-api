package no.ssb.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by rsa on 08.06.2016.
 */
@SpringBootApplication
@EnableScheduling
public class SsbSfuApiApplikasjon {
    public static void main(String[] args) {
        SpringApplication.run(SsbSfuApiApplikasjon.class, args);
    }
}
