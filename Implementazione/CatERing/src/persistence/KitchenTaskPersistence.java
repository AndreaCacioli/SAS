package persistence;

import businesslogic.kitchentask.KitchenTask;
import businesslogic.kitchentask.KitchenTaskEventReceiver;
import businesslogic.kitchentask.ToDoList;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {
    @Override
    public void updateNewListCreated(ToDoList tdl) {
        ToDoList.saveNewToDoList(tdl);
    }

    @Override
    public void updateListEmptied(ToDoList tdl) {
        ToDoList.clearList(tdl);
    }

    @Override
    public void updateNewTaskAdded(KitchenTask kitchenTask) {
        KitchenTask.saveTask(kitchenTask, true);
    }

    @Override
    public void updateTaskRemoved(KitchenTask kitchenTask) {
        KitchenTask.deleteTask(kitchenTask);
    }

    @Override
    public void updateTaskChanged(KitchenTask kitchenTask) {
        KitchenTask.updateTask(kitchenTask);
    }
}
