package by.it.academy.elearning.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "course_t")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BasicEntity {

    public Course(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(unique = true, nullable = false)
    private String name;

}
