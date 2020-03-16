package by.it.academy.elearning.core.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseModel {

    @Column(nullable = false)
    private String name;

    private LocalDate start;
    private LocalDate end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;


}
