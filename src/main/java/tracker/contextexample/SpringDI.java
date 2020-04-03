package tracker.contextexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tracker.inputs.ConsoleInput;
import tracker.trackers.Tracker;

/**
 * Пример Spring DI.
 */
public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConsoleInput.class);
        context.register(Tracker.class);
        context.register(StartUIforContext.class);
        context.refresh();
        StartUIforContext ui = context.getBean(StartUIforContext.class);
        ui.init();
    }
}