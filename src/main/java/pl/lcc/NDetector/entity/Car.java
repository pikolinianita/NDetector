package pl.lcc.NDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;



// example entity, for tests;
@Entity
@Data
public class Car {

    @GeneratedValue
    @Id
    long id;

    String name;
}
