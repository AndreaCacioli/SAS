package businesslogic.kitchentask;

public interface KitchenTaskEventReceiver {
    void updateNewListCreated(ToDoList tdl);

    void updateListEmptied(ToDoList tdl);

    void updateNewTaskAdded(KitchenTask kitchenTask);

    void updateTaskRemoved(KitchenTask kitchenTask);

    void updateTaskChanged(KitchenTask kitchenTask);
}
