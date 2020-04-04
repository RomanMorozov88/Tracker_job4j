package tracker.contextexample;

import org.springframework.stereotype.Component;
import tracker.*;
import tracker.inputs.ConsoleInput;
import tracker.trackers.ITracker;
import tracker.inputs.Input;
import tracker.trackers.Tracker;
import tracker.uis.StartUIinterface;

/**
 * Почти полная копия StartUI за исключением конструктора в котором в качестве параметров указаны
 * конкретные реализации input и tracker.
 */
@Component
public class StartUIforContext implements StartUIinterface {

    private final Input input;
    private final ITracker tracker;
    private boolean stop = true;

    public StartUIforContext(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        menu.setRange();
        do {
            menu.show(System.out::println);
            menu.select(input.ask("Выберите нужный пункт меню: ", menu.range));
        } while (this.stop);
    }

    /**
     * Метод для изменения переменной stop.
     */
    public void exitItem() {
        this.stop = false;
    }

}
