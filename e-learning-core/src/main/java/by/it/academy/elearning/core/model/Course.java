package by.it.academy.elearning.core.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@ToString(callSuper = true, exclude = {"lessons", "students"})
@EqualsAndHashCode(callSuper = false, exclude = {"lessons", "students"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseModel {

    @Column(nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<User> students = new ArrayList<>();

}
