package by.it.academy.elearning.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "course_t")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BasicEntity {

    private String name;
}
