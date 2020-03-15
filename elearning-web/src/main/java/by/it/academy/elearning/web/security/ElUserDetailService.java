package by.it.academy.elearning.web.security;

import by.it.academy.elearning.core.model.ELUser;
import by.it.academy.elearning.core.service.ElUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElUserDetailService implements UserDetailsService {

    private final ElUserService elUserService;

    @Autowired
    public ElUserDetailService(ElUserService elUserService) {
        this.elUserService = elUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ELUser user = find(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()).build();
    }

    private Optional<ELUser> find(String username) {
        return elUserService.findByUsername(username.trim().toLowerCase());
    }
}
