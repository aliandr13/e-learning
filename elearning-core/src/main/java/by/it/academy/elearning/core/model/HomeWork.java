package by.it.academy.elearning.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HomeWork extends BaseModel {

    @OneToOne
    @PrimaryKeyJoinColumn
    private Lesson lesson;
    private String task;
    private String description;


}
