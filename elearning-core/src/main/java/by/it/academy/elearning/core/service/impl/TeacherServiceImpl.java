package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Teacher;
import by.it.academy.elearning.core.repository.TeacherRepository;
import by.it.academy.elearning.core.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl extends BaseCrudService<TeacherRepository, Teacher> implements TeacherService {

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        super(teacherRepository);
    }
}
