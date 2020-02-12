package by.it.academy.elearning;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@NoArgsConstructor
@Slf4j
public class Person {
    private Long id;
    private String name;
    @Autowired
    @Qualifier("privateAddress")
    private Address address;

    public void init() {
        log.info("Person init method");
    }

    public void destroy() {
        log.info("Person destroy method");
    }

}
