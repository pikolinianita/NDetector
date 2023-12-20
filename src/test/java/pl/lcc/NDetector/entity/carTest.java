package pl.lcc.NDetector.entity;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.lcc.NDetector.detector.Detector;
import pl.lcc.NDetector.detector.Operator;
import pl.lcc.NDetector.detector.Stat;
import pl.lcc.NDetector.repo.CarRepository;

import static org.junit.jupiter.api.Assertions.*;
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
            det.amount(Stat.FETCH).mustBe(Operator.EQUAL,0);
            det.evaluate();
    }

}