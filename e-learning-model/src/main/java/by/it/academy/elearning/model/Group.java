package by.it.academy.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    public Group(Long id) {
        this.id = id;
    }

    private Long id;
    private String name;
    private Course course;
    private LocalDate startDate;

}
