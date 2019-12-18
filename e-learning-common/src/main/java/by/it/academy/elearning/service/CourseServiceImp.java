package by.it.academy.elearning.service;

import by.it.academy.elearning.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImp {

    private static final CourseServiceImp INSTANCE = new CourseServiceImp();
    private final List<Course> courses;

    private CourseServiceImp() {
        courses = new ArrayList<>();
        courses.add(new Course(1L, "Java"));
        courses.add(new Course(2L, "Python"));
        courses.add(new Course(3L, "IOS"));
        courses.add(new Course(4L, "Ruby"));
        courses.add(new Course(5L, "Android"));
    }

    public static CourseServiceImp getService() {
        return INSTANCE;
    }

    public List<Course> getAll() {
        return new ArrayList<>(courses);
    }
}
