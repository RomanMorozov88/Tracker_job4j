package tracker;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(ConsoleInput.class);
        context.reg(Tracker.class);
        context.reg(StartUI.class);

        StartUI ui = context.get(StartUI.class);
        ui.init();
    }
}