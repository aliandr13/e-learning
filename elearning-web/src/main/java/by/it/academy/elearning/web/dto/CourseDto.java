package by.it.academy.elearning.web.dto;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.model.Lesson;
import by.it.academy.elearning.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Course course;
    private List<User> students = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();


}
