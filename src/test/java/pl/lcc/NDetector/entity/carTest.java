package pl.lcc.NDetector.entity;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.lcc.NDetector.detector.Detector;
import pl.lcc.NDetector.detector.Operator;
import pl.lcc.NDetector.detector.Stat;
import pl.lcc.NDetector.detector.StatsSnapshot;
import pl.lcc.NDetector.repo.CarRepository;

@SpringBootTest
class carTest {

    @Autowired
    CarRepository cRepo;

    @Autowired
    SessionFactory sf;

    @Test
    void should_do_it(){
            var det = new Detector(sf, "start");
            var c = new Car();
            c.setName("fiat");
            cRepo.save(c);
            det.command(Stat.FETCH).mustBe(Operator.EQUAL,0).evaluate();
            det.evaluate();
    }


    @Test
    void should_do_it2(){
        var det = new Detector(sf, "start");
        var c = new Car();
        c.setName("fiat");
        var c2 = new Car();
        c2.setName("Merc");
        cRepo.save(c);
        cRepo.save(c2);
        det.command(Stat.FETCH).mustBe(Operator.EQUAL,0);
        det.evaluate();
    }

}