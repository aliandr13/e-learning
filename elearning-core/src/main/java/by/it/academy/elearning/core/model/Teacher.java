package by.it.academy.elearning.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("teacher")
public class Teacher extends Person {

    private Double salary;

}
