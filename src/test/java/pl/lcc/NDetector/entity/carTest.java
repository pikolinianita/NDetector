package pl.lcc.NDetector.entity;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.lcc.NDetector.detector.Detector;
import pl.lcc.NDetector.detector.Operator;
import pl.lcc.NDetector.detector.Stat;
import static org.assertj.core.api.Assertions.assertThat;
import pl.lcc.NDetector.repo.CarRepository;

@SpringBootTest
class CarTest {

    @Autowired
    CarRepository cRepo;

    @Autowired
    SessionFactory sf;

    @Test
    void should_do_it(){
            var det = new Detector(sf, "start");
            var c = new Car();
            c.setName("Fiat");
            cRepo.save(c);
            det.command(Stat.FETCH).shouldBe(Operator.EQUAL,1).evaluate();
            assertThat(det.evaluate()).isEqualTo("");

    }


    @Test
    void should_do_it2(){
        var det = new Detector(sf, "start");
        var c = new Car();
        c.setName("Fiat");
        var c2 = new Car();
        c2.setName("Mercedes");
        cRepo.save(c);
        cRepo.save(c2);
        det.command(Stat.FETCH).shouldBe(Operator.EQUAL,1);
        det.evaluate();
        assertThat(det.evaluate()).isNotEqualTo("");
    }

}