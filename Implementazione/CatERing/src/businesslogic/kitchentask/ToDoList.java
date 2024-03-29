package businesslogic.kitchentask;

import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.recipe.Procedure;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ToDoList {
    private ArrayList<KitchenTask> tasks;
    ServiceInfo service;

    public ToDoList(ServiceInfo service) {
        this.service = service;
        tasks = new ArrayList<>();
    }

    public static void saveNewToDoList(ToDoList tdl) {
        for (KitchenTask kitchenTask : tdl.tasks) {
            //Save The Kitchen task itself, this will trigger the save in the ToDoLists table
            KitchenTask.saveTask(kitchenTask, true);
        }
    }

    private static ToDoList loadToDoListWithId(ServiceInfo service) {
        ToDoList ret = new ToDoList(service);
        ArrayList<Integer> taskIds = new ArrayList<>();
        PersistenceManager.executeQuery("SELECT * FROM ToDoLists WHERE idService = " + service.getId(), new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                taskIds.add(rs.getInt("idTask"));
            }
        });
        for (KitchenTask kitchenTask : KitchenTask.getAllTasks()) {
            if (taskIds.contains(kitchenTask.getId())) ret.tasks.add(kitchenTask);
        }
        return ret;
    }

    public static ToDoList loadToDoList(ServiceInfo service) {
        return loadToDoListWithId(service);
    }

    public static void clearList(ToDoList tdl) {
        PersistenceManager.executeUpdate("DELETE FROM ToDoLists WHERE idService = " + tdl.service.getId());
        for (KitchenTask kt : tdl.tasks) {
            KitchenTask.deleteTask(kt);
        }

    }

    public static void saveNewTask(ToDoList currentToDoList, KitchenTask kitchenTask) {
        String update = "INSERT INTO ToDoLists (idService, idTask) VALUES " +
                "(" + currentToDoList.service.getId() +
                ", " + kitchenTask.getId() +
                ");";
        PersistenceManager.executeUpdate(update);
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
        for (KitchenTask kt : tasks) {
            s += kt.toString();
        }
        return s + "\n";
    }

    public ToDoList addFeatures(KitchenTask kitchenTask, Duration esteemTime, Float amount) {
        for (KitchenTask kt : tasks) {
            if (kt.getId() == kitchenTask.getId()) {
                kt.setDuration(esteemTime);
                kt.setAmount(amount);
                break;
            }
        }
        return this;
    }

    public ArrayList<KitchenTask> deleteProcedure(Procedure procedure) {
        ArrayList<KitchenTask> removedTasks = new ArrayList<>();
        for (KitchenTask kt : tasks) {
            if (kt.getProcedure() != null && procedure != null && kt.getProcedure().getName().equals(procedure.getName())) {
                removedTasks.add(kt);
            }
        }
        tasks.removeAll(removedTasks);
        return removedTasks;
    }

    public void clear() {
        tasks.clear();
    }

    public boolean contains(KitchenTask toUpdate) {
        return tasks.contains(toUpdate);
    }

    public void deleteTask(KitchenTask toUpdate) {
        tasks.remove(toUpdate);
    }
}
