package by.it.academy.elearning.web.validation;


import by.it.academy.elearning.core.model.Course;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class CourseValidator implements Validator {

    private static final LocalDate MIN_DATE = LocalDate.of(2010, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(2050, 1, 1);

    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end", "field.empty");

        Course course = (Course) target;

        if (course.getEnd() == null || course.getStart() == null) {
            return;
        }

        if (course.getStart().isAfter(course.getEnd())) {
            errors.rejectValue("start", "dates.overlaps");
        }

        if (course.getStart().isBefore(MIN_DATE)) {
            errors.rejectValue("start", "dates.min");
        }
        if (course.getEnd().isAfter(MAX_DATE)) {
            errors.rejectValue("end", "dates.max");
        }
    }
}
