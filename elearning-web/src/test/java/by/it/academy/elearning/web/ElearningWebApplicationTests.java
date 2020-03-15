package by.it.academy.elearning.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
class ElearningWebApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
      log.info("passwordEncoder {}", passwordEncoder.toString());
      log.warn("pass -> {}", passwordEncoder.encode("pass"));
    }

}
