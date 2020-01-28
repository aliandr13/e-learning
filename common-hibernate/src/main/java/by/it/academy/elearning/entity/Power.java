package by.it.academy.elearning.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Power {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer power;
    public Power(String name, Integer power) {
        this.name = name;
        this.power = power;
    }
}
