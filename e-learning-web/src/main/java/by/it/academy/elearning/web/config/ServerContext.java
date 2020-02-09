package by.it.academy.elearning.web.config;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ServerContext {

    private static ConfigurableApplicationContext context;


    public static void initContext() {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
        context.refresh();
        context.start();
    }

    public static void closeContext() {
        context.close();
    }

    public static <T> T getBean(Class<T> className) {
        return context.getBean(className);
    }

}
