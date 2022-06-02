package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.ToDoList;
import businesslogic.menu.Menu;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;

public class Test1 {
    public static void main(String[] args) {

        try {
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

            ToDoList toDoList = CatERing.getInstance().getKitchenTaskManager().generateToDoList(m);

            //Delete so the database is clear
            CatERing.getInstance().getMenuManager().deleteMenu(m);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
