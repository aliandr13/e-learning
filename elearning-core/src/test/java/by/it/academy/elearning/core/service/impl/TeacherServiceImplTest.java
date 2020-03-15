package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Teacher;
import by.it.academy.elearning.core.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class TeacherServiceImplTest {


    public static final String NAME = "Petr";
    public static final String SURNAME = "Petrov";
    public static final double SALARY = 123.00;
    @Autowired
    private TeacherService teacherService;

    @Test
    void saveTest() {
        Teacher teacher = createNewTeacher();

        assertThat(teacher.getName()).isEqualTo(NAME);
        assertThat(teacher.getSurname()).isEqualTo(SURNAME);
        assertThat(teacher.getSalary()).isEqualTo(SALARY);
        assertThat(teacher.getId()).isNotNull();
        assertThat(teacher.getCreated()).isNotNull();
    }

    @Test
    void findByIdTest() {
        Teacher saved = createNewTeacher();

        Teacher found = teacherService.findById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        assertThat(found.getName()).isEqualTo(saved.getName());
        assertThat(found.getSurname()).isEqualTo(saved.getSurname());
        assertThat(found.getSalary()).isEqualTo(saved.getSalary());
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getCreated()).isEqualTo(saved.getCreated());
    }

    @Test
    void updateTest() {
        Teacher saved = createNewTeacher();

        String updated_name = "UPDATED_NAME";
        saved.setName(updated_name);
        teacherService.update(saved);

        Teacher found = teacherService.findById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        assertThat(found.getName()).isEqualTo(updated_name);
    }

    @Test
    void deleteTest() {
        Teacher saved = createNewTeacher();

        teacherService.delete(saved);

        Optional<Teacher> found = teacherService.findById(saved.getId());

        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    void deleteByIdTest() {
        Teacher saved = createNewTeacher();

        teacherService.delete(saved.getId());

        Optional<Teacher> found = teacherService.findById(saved.getId());

        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    void findAllTest() {
        Teacher saved = createNewTeacher();

        List<Teacher> found = teacherService.findAll();

        assertThat(found).isNotEmpty().hasSize(1);
        assertThat(found.get(0)).isEqualTo(saved);
    }

    private Teacher createNewTeacher() {
        return teacherService.create(buildTeacher());
    }

    private Teacher buildTeacher() {
        return Teacher.builder().name(NAME).surname(SURNAME).salary(SALARY).build();
    }
}