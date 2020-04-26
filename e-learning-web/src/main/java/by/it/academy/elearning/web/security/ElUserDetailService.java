package by.it.academy.elearning.web.security;

import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public ElUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ElUser.elBuilder()
                .id(user.getId())
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();
    }

}
