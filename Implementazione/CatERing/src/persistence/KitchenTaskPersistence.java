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
        KitchenTask.updateTask(kitchenTask);
    }

    @Override
    public void updateTaskRemoved() {
        //TODO remove task from db
    }

    @Override
    public void updateTaskChanged(KitchenTask kitchenTask) {
        //TODO update the task
    }
}
