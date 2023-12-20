package pl.lcc.NDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Generated;

@Entity
@Data
public class Car {

    @GeneratedValue
    @Id
    long id;

    String name;
}
