package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchentask.ToDoList;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuException;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;

public class Test2a {
    public static void main(String[] args) throws UseCaseLogicException, MenuException {

        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        System.out.println("TEST GENERA FOGLIO");
        //Usiamo un menú perché non é possibile risalire al menu del servizio
        Menu m = CatERing.getInstance().getMenuManager().createMenu("Menu da copiare");

        Section antipasti = CatERing.getInstance().getMenuManager().defineSection("Antipasti");
        Section primi = CatERing.getInstance().getMenuManager().defineSection("Primi");
        Section secondi = CatERing.getInstance().getMenuManager().defineSection("Secondi");

        ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(0), antipasti);
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(1), antipasti);
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(2), antipasti);
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(6), secondi);
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(7), secondi);
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(3));
        CatERing.getInstance().getMenuManager().insertItem(recipes.get(4));

        ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().generateToDoList(m);
        System.out.println("BEFORE THE DELETION");
        System.out.println(tdl);
        CatERing.getInstance().getKitchenTaskManager().deleteProcedure(recipes.get(0));
        System.out.println("AFTER THE DELETION");
        System.out.println(tdl);

        CatERing.getInstance().getMenuManager().deleteMenu(m);
    }
}
