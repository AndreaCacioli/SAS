package persistence;

import businesslogic.kitchentask.KitchenTask;
import businesslogic.kitchentask.KitchenTaskEventReceiver;
import businesslogic.kitchentask.ToDoList;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {
    @Override
    public void updateNewListCreated(ToDoList tdl) {
        //TODO Add the list to the database
        ToDoList.saveNewToDoList(tdl);
    }

    @Override
    public void updateListEmptied() {
        //TODO Make sure list is empty
    }

    @Override
    public void updateNewTaskAdded() {
        //TODO add task to db
    }

    @Override
    public void updateTaskRemoved() {
        //TODO remove task from db
    }

    @Override
    public void updateTaskChanged() {
        //TODO update the task
    }
}
