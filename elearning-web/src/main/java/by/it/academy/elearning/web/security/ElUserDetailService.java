package by.it.academy.elearning.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ElUser user = find(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles()).build();
    }

    private Optional<ElUser> find(String name) {
        if (name.equalsIgnoreCase("user")) {
            return Optional.of(new ElUser("user", passwordEncoder.encode("pass"), "USER"));
        }
        if (name.equalsIgnoreCase("admin")) {
            return Optional.of(new ElUser("admin", passwordEncoder.encode("pass"), "ADMIN"));
        }
        return Optional.empty();
    }
}
