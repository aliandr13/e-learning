package by.it.academy.elearning.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"by.it.academy.elearning.web", "by.it.academy.elearning.core"})
public class ELearningWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearningWebApplication.class, args);
    }

}
