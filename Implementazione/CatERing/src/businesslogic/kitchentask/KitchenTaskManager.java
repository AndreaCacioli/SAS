package businesslogic.kitchentask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.recipe.Procedure;
import businesslogic.turn.Turn;
import businesslogic.turn.TurnTable;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class KitchenTaskManager {

    ToDoList currentToDoList;
    ArrayList<KitchenTaskEventReceiver> receivers;

    public KitchenTaskManager() {
        receivers = new ArrayList<>();
    }

    public ToDoList generateToDoList(ServiceInfo service) throws UseCaseLogicException, ServiceException {
        if (service.hasToDoList()) {
           throw new ServiceException();
        }
        Menu m = service.getMenu();
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef()) throw new UseCaseLogicException();
        if (!m.isOwner(CatERing.getInstance().getUserManager().getCurrentUser())) throw new UseCaseLogicException();

        currentToDoList = new ToDoList(service);
        ObservableList<MenuItem> freeItems = m.getFreeItems();
        for (MenuItem item : freeItems) {
            KitchenTask kitchenTask = new KitchenTask(item);
            currentToDoList.add(kitchenTask);
        }
        ObservableList<Section> sections = m.getSections();
        for (Section section : sections) {
            ObservableList<MenuItem> items = section.getItems();
            for (MenuItem item : items) {
                KitchenTask kitchenTask = new KitchenTask(item);
                currentToDoList.add(kitchenTask);
            }
        }
        notifyNewListCreated();
        return currentToDoList;
    }

    public ToDoList openToDoList(ServiceInfo service) throws UseCaseLogicException, ServiceException {
        if (!service.hasToDoList()) {
          throw new ServiceException();
        }
        Menu m = service.getMenu();
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef()) throw new UseCaseLogicException();
        if (!m.isOwner(CatERing.getInstance().getUserManager().getCurrentUser())) throw new UseCaseLogicException();
        currentToDoList = ToDoList.loadToDoList(service);
        return currentToDoList;
    }

    public KitchenTask addProcedure(Procedure procedure) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        KitchenTask kitchenTask = new KitchenTask(procedure);
        currentToDoList.add(kitchenTask);
        notifyNewTaskAdded(kitchenTask);
        return kitchenTask;
    }

    public void sortToDoList(Comparator<KitchenTask> comparator) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        currentToDoList.sort(comparator);
    }

    public TurnTable getTurnTable() {
        return CatERing.getInstance().getTurnTableManager().getTurnTable();
    }


    public KitchenTask addTask(Procedure procedure, ArrayList<User> cooks, Turn turn) throws UseCaseLogicException, ServiceException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        KitchenTask kitchenTask = new KitchenTask(procedure);
        if (cooks != null) {
            if (turn != null) {
                kitchenTask.setTurn(turn);
                kitchenTask.addCooks(cooks);
            } else {
                throw new UseCaseLogicException();
            }
        } else {
            if (turn != null) {
                kitchenTask.setTurn(turn);
            }
        }
        currentToDoList.add(kitchenTask);
        notifyNewTaskAdded(kitchenTask);
        return kitchenTask;
    }


    public ToDoList addFeatures(KitchenTask kitchenTask, Duration esteemTime, Float amount) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null || !currentToDoList.contains(kitchenTask))
            throw new UseCaseLogicException();
        currentToDoList.addFeatures(kitchenTask, esteemTime, amount);
        notifyTaskChanged(kitchenTask);
        return currentToDoList;
    }

    public void deleteProcedure(Procedure procedure) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        ArrayList<KitchenTask> removedTasks = currentToDoList.deleteProcedure(procedure);
        for (KitchenTask kt : removedTasks) {
            notifyTaskRemoved(kt);
        }
    }

    public void updateTask(KitchenTask toUpdate, ArrayList<User> cooks) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        toUpdate.updateTask(cooks);
        notifyTaskChanged(toUpdate);
    }

    public void updateTask(KitchenTask toUpdate, Turn turn) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        toUpdate.updateTask(turn);
        notifyTaskChanged(toUpdate);
    }

    public void updateTask(KitchenTask toUpdate, Procedure procedure) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null)
            throw new UseCaseLogicException();
        toUpdate.updateTask(procedure);
        notifyTaskChanged(toUpdate);
    }

    public ToDoList emptyToDoList(ServiceInfo service) throws UseCaseLogicException, ServiceException {
        openToDoList(service);
        notifyListEmptied();
        currentToDoList.clear();
        return currentToDoList;
    }

    public void deleteTask(KitchenTask toUpdate) throws UseCaseLogicException {
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null || !currentToDoList.contains(toUpdate))
            throw new UseCaseLogicException();
        currentToDoList.deleteTask(toUpdate);
        notifyTaskRemoved(toUpdate);
    }

    //////////////////////////Event Receivers Methods//////////////////////////////

    public void addEventReceiver(KitchenTaskEventReceiver kitchenTaskEventReceiver) {
        this.receivers.add(kitchenTaskEventReceiver);
    }

    private void notifyNewListCreated() {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateNewListCreated(currentToDoList);
        }
    }

    private void notifyListEmptied() {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateListEmptied(currentToDoList);
        }
    }

    public ToDoList getCurrentToDoList() {
        return currentToDoList;
    }

    private void notifyNewTaskAdded(KitchenTask kitchenTask) {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateNewTaskAdded(kitchenTask);
        }
    }

    private void notifyTaskRemoved(KitchenTask kitchenTask) {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateTaskRemoved(kitchenTask);
        }
    }

    private void notifyTaskChanged(KitchenTask kitchenTask) {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateTaskChanged(kitchenTask);
        }
    }


}
