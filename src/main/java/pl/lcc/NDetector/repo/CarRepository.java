package pl.lcc.NDetector.repo;

import org.springframework.data.repository.CrudRepository;
import pl.lcc.NDetector.entity.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
