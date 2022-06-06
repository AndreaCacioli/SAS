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

    public ToDoList generateToDoList(ServiceInfo service) throws UseCaseLogicException {
        ////////////Aggiunta///////////
        if (service.hasToDoList())
        {
            System.err.println("The service already has a tdl associated to it!");
            return null;
        }

        Menu m = service.getMenu();
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef()) throw new UseCaseLogicException();
        if (!m.isOwner(CatERing.getInstance().getUserManager().getCurrentUser())) throw new UseCaseLogicException();
        ToDoList tdl = new ToDoList(service.getId());
        ObservableList<MenuItem> freeItems = m.getFreeItems();
        for (MenuItem item : freeItems) {
            KitchenTask kitchenTask = new KitchenTask(item);
            tdl.add(kitchenTask);
        }
        ObservableList<Section> sections = m.getSections();
        for (Section section : sections) {
            ObservableList<MenuItem> items = section.getItems();
            for (MenuItem item : items) {
                KitchenTask kitchenTask = new KitchenTask(item);
                tdl.add(kitchenTask);
            }
        }
        currentToDoList = tdl;
        notifyNewListCreated();
        return tdl;
    }

    public ToDoList openToDoList(ServiceInfo service) throws UseCaseLogicException {
        if (!service.hasToDoList()) {
            //TODO riguardare se é fatto bene
            System.err.println("The current service doesn't have a list associated");
            return null;
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


    public KitchenTask addTask(Procedure procedure, ArrayList<User> cooks, Turn turn) throws UseCaseLogicException, CookUnavailableException {
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


    public ToDoList addFeatures(KitchenTask kitchenTask, Duration esteemTime, float amount) {
        notifyTaskChanged(kitchenTask);
        return currentToDoList.addFeatures(kitchenTask, esteemTime, amount);
    }

    public void deleteProcedure(Procedure procedure) {
        notifyTaskRemoved();
        currentToDoList.deleteProcedure(procedure);
    }

    public void updateTask(KitchenTask toUpdate, ArrayList<User> cooks) throws UseCaseLogicException {
        notifyTaskChanged(toUpdate);
        toUpdate.updateTask(cooks);
    }

    public void updateTask(KitchenTask toUpdate, Turn turn) {
        notifyTaskChanged(toUpdate);
        toUpdate.updateTask(turn);
    }

    public void updateTask(KitchenTask toUpdate, Procedure procedure) {
        notifyTaskChanged(toUpdate);
        toUpdate.updateTask(procedure);
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

    private void notifyNewTaskAdded(KitchenTask kitchenTask) {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateNewTaskAdded(kitchenTask);
        }
    }

    private void notifyTaskRemoved() {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateTaskRemoved();
        }
    }

    private void notifyTaskChanged(KitchenTask kitchenTask) {
        for (KitchenTaskEventReceiver receiver : receivers) {
            receiver.updateTaskChanged(kitchenTask);
        }
    }

    public ToDoList emptyToDoList(ServiceInfo service) throws UseCaseLogicException {
        openToDoList(service);
        notifyListEmptied();
        currentToDoList.clear();
        return currentToDoList;
    }
}
