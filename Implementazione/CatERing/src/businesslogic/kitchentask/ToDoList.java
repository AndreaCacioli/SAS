package businesslogic.kitchentask;

import businesslogic.menu.Menu;

import java.time.Duration;
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

    public ToDoList addFeatures(KitchenTask kitchenTask, Duration esteemTime, Float amount)
    {
        for(KitchenTask kt : tasks)
        {
            if(kt.getProcedure().getName().compareTo(kitchenTask.getProcedure().getName()) == 0)
            {
                if(esteemTime != null) kt.setDuration(esteemTime);
                if(amount != null) kt.setAmount(amount);
                break;
            }
        }
        return this;
    }
}
