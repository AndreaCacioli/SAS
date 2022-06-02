package businesslogic.kitchentask;

import businesslogic.menu.Menu;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<KitchenTask> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void add(KitchenTask kitchenTask) {
        tasks.add(kitchenTask);
    }
}
