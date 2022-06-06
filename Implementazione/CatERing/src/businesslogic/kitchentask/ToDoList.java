package businesslogic.kitchentask;

import businesslogic.menu.Menu;
import businesslogic.recipe.Procedure;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ToDoList {
    private ArrayList<KitchenTask> tasks;
    int serviceId;

    public ToDoList(int id)
    {
        serviceId = id;
        tasks = new ArrayList<>();
    }

    public static void saveNewToDoList(ToDoList tdl) {
        for(KitchenTask kitchenTask : tdl.tasks)
        {
            //Save The Kitchen task itself
            KitchenTask.saveTask(kitchenTask);

            //Save Kitchen Task position
            String newTDLUpdate = "INSERT INTO catering.ToDoLists (idService, idCompito) VALUES (" + tdl.serviceId +
                    ", " + kitchenTask.getId() +
                    ");";
            PersistenceManager.executeUpdate(newTDLUpdate);
        }


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
        tasks.removeIf(kt -> kt.getProcedure().getName().equals(procedure.getName()));
    }
}
