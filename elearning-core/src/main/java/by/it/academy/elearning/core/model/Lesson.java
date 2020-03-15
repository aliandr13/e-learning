package by.it.academy.elearning.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseModel{

    private LocalDate date;
    private String topic;
    private String description;

}