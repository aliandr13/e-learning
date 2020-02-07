package by.it.academy.elearning;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@NoArgsConstructor
@Component("person")
public class Person {
    @Value("555")
    private Long id;
    @Value("Ivan")
    private String name;
    @Value("Ivanov")
    private String surname;
    @Autowired(required = false)
    private IAddress address;

    public void initPerson() {
        log.info("init person name: {}", name);
    }

    public void destroyPerson() {
        log.info("Destroy person id {}", id);
    }
}
