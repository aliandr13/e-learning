package by.it.academy.elearning;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.it.academy.elearning")
public class NoXmlConfiguration {

    @Bean(value = "personBean",
            autowire = Autowire.NO,
            initMethod = "initPerson",
            destroyMethod = "destroyPerson"
    )
    public Person person() {
        return new Person();
    }
}
