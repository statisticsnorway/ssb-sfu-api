package no.ssb.api.repository;

import no.ssb.api.database.KatKlassSn2007;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by rsa on 19.09.2016.
 */
@Repository
public interface KlassKopiRepository extends JpaRepository<KatKlassSn2007, Long> {
    @Modifying
    @Query("UPDATE KatKlassSn2007 c SET c.utg = 1")
    int setKatKlassSn2007UtgTil1();
}
