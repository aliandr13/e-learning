package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.model.UserRole;
import by.it.academy.elearning.core.repository.UserRepository;
import by.it.academy.elearning.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl extends BaseCrudService<UserRepository, User> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository, log);
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String actualEmail = email.trim().toLowerCase();
        log.debug("Finding user by email {}", actualEmail);
        return userRepository.findByEmail(actualEmail);
    }

    @Override
    public List<User> getTeachers() {
        log.info("Get teachers");
        List<User> teachers = userRepository.findByRole(UserRole.TEACHER);
        log.debug("Result {}", teachers);
        return teachers;
    }
}
