package by.it.academy.elearning.service;

import by.it.academy.elearning.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private final Map<String, User> users = new ConcurrentHashMap<>();

    private static final AtomicLong idSequence = new AtomicLong(10);

    private UserServiceImpl() {
        users.put("tom", new User(1L, "tom", "tom001", "admin"));
        users.put("jerry", new User(2L, "jerry", "jerry001", "user"));
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUser(String login, String password) {
        User user = users.get(login);
        if (user != null && password.equals(user.getPassword())) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return users.values().stream()
                .filter(u -> u.getId().equals(id)).findFirst();
    }
}
