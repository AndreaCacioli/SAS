package businesslogic.kitchentask;

public interface KitchenTaskEventReceiver {
    void updateNewListCreated(ToDoList tdl);

    void updateListEmptied(ToDoList tdl);

    void updateNewTaskAdded(KitchenTask kitchenTask);

    void updateTaskRemoved();

    void updateTaskChanged(KitchenTask kitchenTask);
}
