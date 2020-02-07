package by.it.academy.elearning;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainWithAnnotationConfiguration implements BeanNameAware {

    private String beanName;

    @Autowired
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainWithAnnotationConfiguration.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext("by.it.academy.elearning");

        MainWithAnnotationConfiguration main = ctx.getBean(MainWithAnnotationConfiguration.class);

        log.info("Name = {}", main.getBeanName());
        log.info("class = {}", main.getClass());
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
