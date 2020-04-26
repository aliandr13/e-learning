package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.User;
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
class UserRepositoryTest {

    public static final String NAME_NOT_IN_DB = "Not_in_db_user";

    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    private UserRepository userRepository;
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
    void findByUsername_shouldReturnEmpty() {
        dbSetupTracker.skipNextLaunch();
        Optional<User> user = userRepository.findByEmail(NAME_NOT_IN_DB);
        assertThat(user).isEmpty();
    }

    @Test
    void findByUsername_shouldReturnUser() {
        dbSetupTracker.skipNextLaunch();
        Optional<User> user = userRepository.findByEmail(EMAIL_1);
        assertThat(user).isNotEmpty().get()
                .hasFieldOrPropertyWithValue(NAME_PROPERTY, NAME_1)
                .hasFieldOrPropertyWithValue(EMAIL_PROPERTY, EMAIL_1)
                .hasFieldOrPropertyWithValue(SURNAME_PROPERTY, SURNAME_1)
                .hasFieldOrPropertyWithValue(PASSWORD_PROPERTY, PASS_1)
                .hasFieldOrPropertyWithValue(ROLE_PROPERTY, ROLE_1);
    }

    @Test
    void existsByUsername_shouldReturnTrue() {
        dbSetupTracker.skipNextLaunch();
        Boolean exists = userRepository.existsByEmail(EMAIL_1);
        assertThat(exists).isTrue();
    }

    @Test
    void existsByUsername_shouldReturnFalse() {
        dbSetupTracker.skipNextLaunch();
        Boolean exists = userRepository.existsByEmail(NAME_NOT_IN_DB);
        assertThat(exists).isFalse();
    }

    @Test
    void findByRole_shouldReturnList() {
        dbSetupTracker.skipNextLaunch();
        List<User> byRole = userRepository.findByRole(ROLE_1);
        assertThat(byRole).isNotEmpty().hasSize(1);
        assertThat(byRole.get(0)).hasFieldOrPropertyWithValue("role", ROLE_1);
    }

}