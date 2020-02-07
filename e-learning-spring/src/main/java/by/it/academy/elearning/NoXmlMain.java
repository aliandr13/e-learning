package by.it.academy.elearning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoXmlMain {

    @Autowired

    private PersonService personService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoXmlMain.class);
        applicationContext.register(NoXmlConfiguration.class);
        applicationContext.getEnvironment().getPropertySources().addLast(new SimpleCommandLinePropertySource(args));
        applicationContext.refresh();
        applicationContext.start();
        try {
            NoXmlMain noXmlMain = applicationContext.getBean(NoXmlMain.class);
            log.info("Name {}", noXmlMain.personService.getPersonName());
        } catch (Exception e) {
            log.error("Error ", e);
        } finally {
            applicationContext.close();
        }

    }

}
