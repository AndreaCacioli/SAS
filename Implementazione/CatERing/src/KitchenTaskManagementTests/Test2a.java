package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.ServiceException;
import businesslogic.kitchentask.ToDoList;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuException;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;

public class Test2a {
    public static void main(String[] args) throws UseCaseLogicException, MenuException, ServiceException {

        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        System.out.println("TEST GENERA FOGLIO");
        //Get menu from service
        var service = ServiceInfo.loadServiceInfoForEvent(1, 2);
        ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().openToDoList(service);
        ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();

        System.out.println("BEFORE THE DELETION");
        System.out.println(tdl);
        System.out.println("Deleting " + recipes.get(7));
        CatERing.getInstance().getKitchenTaskManager().deleteProcedure(recipes.get(7));
        System.out.println("AFTER THE DELETION");
        System.out.println(tdl);
    }
}
