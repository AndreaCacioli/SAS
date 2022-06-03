package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.CookUnavailableException;
import businesslogic.kitchentask.ToDoList;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuException;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import businesslogic.turn.TurnTable;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Test5b {
    public static void main(String[] args) throws UseCaseLogicException, CookUnavailableException, MenuException {
        {

            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("TEST GENERA FOGLIO");
            //Get menu from service
            var service = ServiceInfo.loadServiceInfoForEvent(1, 2);
            ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().generateToDoList(service);

            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            TurnTable tt = CatERing.getInstance().getKitchenTaskManager().getTurnTable();

            CatERing.getInstance().getUserManager().fakeLogin("Marinella");

            ArrayList<User> cooks = new ArrayList<>();
            cooks.add(CatERing.getInstance().getUserManager().getCurrentUser());

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            var toUpdate = CatERing.getInstance().getKitchenTaskManager().addTask(recipes.get(2),
                    null,
                    null);

            System.out.println(tdl);

        }
    }
}
