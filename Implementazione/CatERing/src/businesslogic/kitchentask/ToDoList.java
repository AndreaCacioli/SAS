package businesslogic.kitchentask;

import businesslogic.menu.Menu;
import businesslogic.recipe.Procedure;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ToDoList {
    private ArrayList<KitchenTask> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public static void saveNewToDoList(ToDoList tdl) {
        String newTDLUpdate = "INSERT INTO catering.ToDoLists (idService, idCompito) VALUES (?, ?);";

        //TODO finish the database update

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

    public void deleteProcedure(Procedure procedure) {
        //TODO change if we do not identify the procedure/kitchen task with an id
        tasks.removeIf(kt -> kt.getProcedure().getName().equals(procedure.getName()));
    }
}
