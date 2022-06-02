package businesslogic.kitchentask;

import businesslogic.menu.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ToDoList {
    private ArrayList<KitchenTask> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void add(KitchenTask kitchenTask) {
        tasks.add(kitchenTask);
    }

    public void sort(Comparator<KitchenTask> comparator) {
        Collections.sort(tasks, comparator);
    }

    @Override
    public String toString() {
        String s = "ToDoList:\n";
        for (KitchenTask kt : tasks)
        {
            s += kt.toString();
        }
        return s + "\n";
    }
}
