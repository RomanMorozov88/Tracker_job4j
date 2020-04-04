package tracker.contextexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Пример Spring DI.
 */
public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("tracker");
        context.refresh();
        StartUIforContext ui = context.getBean(StartUIforContext.class);
        ui.init();
    }
}