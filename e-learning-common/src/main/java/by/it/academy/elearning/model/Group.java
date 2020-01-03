package by.it.academy.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private Long id;
    private String name;
    private Course course;
    private LocalDate startDate;

}
