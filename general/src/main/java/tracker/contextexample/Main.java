package tracker.contextexample;

import tracker.inputs.ConsoleInput;
import tracker.trackers.Tracker;

/**
 * Пример работы с классом Context.
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(ConsoleInput.class);
        context.reg(Tracker.class);
        context.reg(StartUIforContext.class);

        StartUIforContext ui = context.get(StartUIforContext.class);
        ui.init();
    }
}