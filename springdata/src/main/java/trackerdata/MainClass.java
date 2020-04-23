package trackerdata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tracker.inputs.ConsoleInput;
import tracker.uis.StartUI;
import trackerdata.trackers.TrackerJDBCtemplate;

/**
 * Пример запуска приложения с тем или иным трэкером.
 */
public class MainClass {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-context.xml");
        TrackerJDBCtemplate tracker = context.getBean(TrackerJDBCtemplate.class);
        ConsoleInput input = context.getBean(ConsoleInput.class);

        StartUI start = new StartUI(input, tracker);
        start.init();

    }

}
