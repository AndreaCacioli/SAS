package businesslogic.kitchentask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import javafx.collections.ObservableList;

public class KitchenTaskManager {
    public ToDoList generateToDoList(Menu m) throws UseCaseLogicException {
        if(!CatERing.getInstance().getUserManager().getCurrentUser().isChef()) throw new UseCaseLogicException();
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
        return tdl;
    }
}
