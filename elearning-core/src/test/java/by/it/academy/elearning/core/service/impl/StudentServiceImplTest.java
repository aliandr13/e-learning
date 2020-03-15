package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Student;
import by.it.academy.elearning.core.service.StudentService;
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
class StudentServiceImplTest {


    public static final String NAME = "Ivan";
    public static final String SURNAME = "Ivanov";
    public static final double MARK = 7.5;
    @Autowired
    private StudentService studentService;

    @Test
    void saveTest() {
        Student student = createNewStudent();

        assertThat(student.getName()).isEqualTo(NAME);
        assertThat(student.getSurname()).isEqualTo(SURNAME);
        assertThat(student.getMark()).isEqualTo(MARK);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getCreated()).isNotNull();
    }

    @Test
    void findByIdTest() {
        Student saved = createNewStudent();

        Student found = studentService.findById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        assertThat(found.getName()).isEqualTo(saved.getName());
        assertThat(found.getSurname()).isEqualTo(saved.getSurname());
        assertThat(found.getMark()).isEqualTo(saved.getMark());
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getCreated()).isEqualTo(saved.getCreated());
    }

    @Test
    void updateTest() {
        Student saved = createNewStudent();

        String updated_name = "UPDATED_NAME";
        saved.setName(updated_name);
        saved.setMark(10.0);
        studentService.update(saved);

        Student found = studentService.findById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        assertThat(found.getName()).isEqualTo(updated_name);
        assertThat(found.getMark()).isEqualTo(10.0);
    }

    @Test
    void deleteTest() {
        Student saved = createNewStudent();

        studentService.delete(saved);

        Optional<Student> found = studentService.findById(saved.getId());

        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    void deleteByIdTest() {
        Student saved = createNewStudent();

        studentService.delete(saved.getId());

        Optional<Student> found = studentService.findById(saved.getId());

        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    void findAllTest() {
        Student saved = createNewStudent();

        List<Student> found = studentService.findAll();

        assertThat(found).isNotEmpty().hasSize(1);
        assertThat(found.get(0)).isEqualTo(saved);
    }

    private Student createNewStudent() {
        return studentService.create(buildStudent());
    }

    private Student buildStudent() {
        return Student.builder().name(NAME).surname(SURNAME).mark(MARK).build();
    }
}