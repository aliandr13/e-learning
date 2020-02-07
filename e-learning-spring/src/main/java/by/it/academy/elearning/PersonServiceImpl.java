package by.it.academy.elearning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private Person person;

    @PostConstruct
    public void init() {
        log.info("Person service post construct");
    }

    @Override
    public String getPersonName() {
        return person.getName();
    }

    @PreDestroy
    public void destroy() {
        log.info("Person service pre destroy");
    }
}
