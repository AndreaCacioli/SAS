package businesslogic.kitchentask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.recipe.Procedure;
import businesslogic.turn.Turn;
import businesslogic.turn.TurnTable;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.util.Comparator;

public class KitchenTaskManager {

    ToDoList currentToDoList;

    public ToDoList generateToDoList(Menu m) throws UseCaseLogicException {
        if(!CatERing.getInstance().getUserManager().getCurrentUser().isChef()) throw new UseCaseLogicException();
        if(!m.isOwner(CatERing.getInstance().getUserManager().getCurrentUser())) throw new UseCaseLogicException();
        ToDoList tdl = new ToDoList();
        ObservableList<MenuItem> freeItems = m.getFreeItems();
        for(MenuItem item : freeItems)
        {
            KitchenTask kitchenTask = new KitchenTask(item);
            tdl.add(kitchenTask);
        }
        ObservableList<Section> sections = m.getSections();
        for (Section section : sections) {
            ObservableList<MenuItem> items = section.getItems();
            for(MenuItem item : items)
            {
                KitchenTask kitchenTask = new KitchenTask(item);
                tdl.add(kitchenTask);
            }
        }
        currentToDoList = tdl;
        return tdl;
    }

    public KitchenTask addProcedure(Procedure procedure) throws UseCaseLogicException {
        if(!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null) throw new UseCaseLogicException();
        KitchenTask kitchenTask = new KitchenTask(procedure);
        currentToDoList.add(kitchenTask);
        return kitchenTask;
    }

    public void sortToDoList(Comparator<KitchenTask> comparator) throws UseCaseLogicException {
        if(!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null) throw new UseCaseLogicException();
        currentToDoList.sort(comparator);
    }

    public TurnTable getTurnTable() {
        return CatERing.getInstance().getTurnTableManager().getTurnTable();
    }


    public KitchenTask addTask(Procedure procedure, User cook, Turn turn) throws UseCaseLogicException, CookUnavailableException {
        if(!CatERing.getInstance().getUserManager().getCurrentUser().isChef() || currentToDoList == null) throw new UseCaseLogicException();
        if(!cook.isCook()) throw new UseCaseLogicException();
        KitchenTask kitchenTask = new KitchenTask(procedure);
        if(cook != null)
        {
            if(turn != null && turn.isAvailable(cook))
            {
                kitchenTask.setCook(cook);
                kitchenTask.setTurn(turn);
            }
            else{
                throw new CookUnavailableException();
            }
        }
        else {
            if (turn != null) {
                kitchenTask.setTurn(turn);
            }
        }
        currentToDoList.add(kitchenTask);
        return kitchenTask;
    }


    public ToDoList addFeatures(KitchenTask kitchenTask, Duration esteemTime, float amount) {
        return currentToDoList.addFeatures(kitchenTask, esteemTime, amount);
    }
}
