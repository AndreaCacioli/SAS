package businesslogic.kitchentask;

public interface KitchenTaskEventReceiver {
    void updateNewListCreated(ToDoList tdl);

    void updateListEmptied();

    void updateNewTaskAdded();

    void updateTaskRemoved();

    void updateTaskChanged();
}
