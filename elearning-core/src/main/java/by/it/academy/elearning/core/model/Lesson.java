package by.it.academy.elearning.core.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@ToString(callSuper = true, exclude = {"course", "studentWorks"})
@EqualsAndHashCode(callSuper = false, exclude = {"course", "studentWorks"})
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String topic;
    private String task;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private List<StudentWork> studentWorks = Collections.emptyList();

}
