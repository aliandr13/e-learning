package by.it.academy.elearning.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("student")
public class Student extends Person {

    private Double mark;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

}
