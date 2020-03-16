package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.model.UserRole;
import by.it.academy.elearning.core.service.UserService;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.CompositeOperation;
import com.ninja_squad.dbsetup.operation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static by.it.academy.elearning.core.repository.utils.CommonOperations.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class UserServiceImplTest {

    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        Operation operation = CompositeOperation.sequenceOf(
                DELETE_ALL,
                ADD_USERS
        );
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);

        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    void saveTest() {
        User build = User.builder()
                .email("email@email.com")
                .name("new name")
                .surname("new surname")
                .password("new Password")
                .role(UserRole.ADMIN).build();

        // when
        User user = userService.create(build);
        Optional<User> found = userService.findById(user.getId());

        // then
        assertThat(found).isNotEmpty().get()
                .hasFieldOrPropertyWithValue(NAME_PROPERTY, build.getName())
                .hasFieldOrPropertyWithValue(EMAIL_PROPERTY, build.getEmail())
                .hasFieldOrPropertyWithValue(SURNAME_PROPERTY, build.getSurname())
                .hasFieldOrPropertyWithValue(PASSWORD_PROPERTY, build.getPassword())
                .hasFieldOrPropertyWithValue(ROLE_PROPERTY, build.getRole());
        assertThat(user).isEqualTo(found.get());
    }

    @Test
    void findByIdTest() {
        Optional<User> found = userService.findById(1L);

        assertThat(found).isNotEmpty().get()
                .hasFieldOrPropertyWithValue(NAME_PROPERTY, NAME_1)
                .hasFieldOrPropertyWithValue(EMAIL_PROPERTY, EMAIL_1)
                .hasFieldOrPropertyWithValue(SURNAME_PROPERTY, SURNAME_1)
                .hasFieldOrPropertyWithValue(PASSWORD_PROPERTY, PASS_1)
                .hasFieldOrPropertyWithValue(ROLE_PROPERTY, ROLE_1);
    }

//    @Test
//    void updateTest() {
//        User saved = createNewTeacher();
//
//        String updated_name = "UPDATED_NAME";
//        saved.setName(updated_name);
//        userService.update(saved);
//
//        User found = userService.findById(saved.getId())
//                .orElseThrow(() -> new RuntimeException("Not found"));
//
//        assertThat(found.getName()).isEqualTo(updated_name);
//    }

//    @Test
//    void deleteTest() {
//        User saved = createNewTeacher();
//
//        userService.delete(saved);
//
//        Optional<User> found = userService.findById(saved.getId());
//
//        assertThat(found.isEmpty()).isTrue();
//    }
//
//    @Test
//    void deleteByIdTest() {
//        User saved = createNewTeacher();
//
//        userService.delete(saved.getId());
//
//        Optional<User> found = userService.findById(saved.getId());
//
//        assertThat(found.isEmpty()).isTrue();
//    }

    @Test
    void findAllTest() {
        dbSetupTracker.skipNextLaunch();

        List<User> found = userService.findAll();

        assertThat(found).isNotEmpty().hasSize(2);
    }

}