package by.it.academy.elearning.web.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ElUser extends User {

    @Getter
    private final Long id;
    @Getter
    private boolean isTeacher;
    @Getter
    private boolean isStudent;
    @Getter
    private boolean isAdmin;

    public ElUser(Long id, String username, String password, List<GrantedAuthority> authorities, boolean isTeacher, boolean isStudent, boolean isAdmin) {
        super(username, password, authorities);
        this.id = id;
        this.isAdmin = isAdmin;
        this.isTeacher = isTeacher;
        this.isStudent = isStudent;
    }

    public static UserBuilder elBuilder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String username;
        private String password;
        private String role;
        private List<GrantedAuthority> authorities;

        public UserBuilder id(Long id) {
            Assert.notNull(id, "id cannot be null");
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public UserBuilder roles(String role) {
            this.role = role;
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
            return authorities(authorities);
        }

        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public UserDetails build() {
            return new ElUser(id, username, password, authorities, "TEACHER".equals(role), "STUDENT".equals(role), "ADMIN".equals(role));
        }

    }

}
