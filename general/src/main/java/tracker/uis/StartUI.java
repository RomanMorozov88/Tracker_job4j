package tracker.uis;

import org.springframework.stereotype.Component;
import tracker.MenuTracker;
import tracker.trackers.ITracker;
import tracker.inputs.Input;

/**
 * @version $Id$
 * @since 0.1
 */
@Component
public class StartUI implements StartUIinterface {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    /**
     * Переменная для выхода из пррограммы.
     */
    private boolean stop = true;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
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