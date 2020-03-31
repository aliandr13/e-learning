package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class UserServiceImplTest {

    @Mock
    private  UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail_withWhiteSpaces() {
        // given
        User value = new User();
        value.setEmail("email");
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(value));
        Mockito.when(userRepository.findByEmail("  email   ")).thenReturn(Optional.empty());

        // when
        Optional<User> user = userService.findByEmail("  email   ");

        // then
        assertEquals("email", user.orElseThrow().getEmail());
    }

    @Test
    public void testFindByEmail_withUpperCase() {
        // given
        User value = new User();
        value.setEmail("email");
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(value));
        Mockito.when(userRepository.findByEmail("EMAIL")).thenReturn(Optional.empty());

        // when
        Optional<User> user = userService.findByEmail("EMAIL");

        // then
        assertEquals("email", user.orElseThrow().getEmail());
        Mockito.verify(userRepository).findByEmail("email");
    }

    @Test
    public void testFindByEmail() {
        // given
        User user = new User();
        user.setEmail("email");
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));

        // when
        Optional<User> userFromBD = userService.findByEmail("email");

        // then
        assertTrue(userFromBD.isPresent());
        assertEquals("email", userFromBD.get().getEmail());
    }

    @Test
    public void testFindByEmail_notFound() {
        // given
        User user = new User();
        user.setEmail("email");
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.empty());

        // when
        Optional<User> userFromBD = userService.findByEmail("email");

        // then
        assertTrue(userFromBD.isEmpty());
    }

}