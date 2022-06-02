package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.CookUnavailableException;
import businesslogic.kitchentask.KitchenTask;
import businesslogic.kitchentask.ToDoList;
import businesslogic.menu.Menu;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import businesslogic.turn.TurnTable;
import javafx.collections.ObservableList;

import java.util.Comparator;

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

            ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().generateToDoList(m);

            System.out.println(tdl);

            //CatERing.getInstance().getKitchenTaskManager().addProcedure(recipes.get(5));
            Comparator<KitchenTask> comparatorCookName = new Comparator<KitchenTask>() {
                public int compare(KitchenTask o1, KitchenTask o2) {
                    if (o1.getCook() != null && o2.getCook() != null) {
                        return o1.getCook().getUserName().compareTo(o2.getCook().getUserName());
                    } else {
                        return 0;
                    }
                }
            };
            Comparator<KitchenTask> comparatorProcedureName = new Comparator<KitchenTask>() {
                public int compare(KitchenTask o1, KitchenTask o2) {
                    if (o1.getProcedure() != null && o2.getProcedure() != null) {
                        return o1.getProcedure().getName().compareTo(o2.getProcedure().getName());
                    } else {
                        return 0;
                    }
                }
            };
            CatERing.getInstance().getKitchenTaskManager().sortToDoList(comparatorProcedureName);
            TurnTable tt = CatERing.getInstance().getKitchenTaskManager().getTurnTable();

            Turn turn = tt.getTurnById(1);

            KitchenTask toUpdate = CatERing.getInstance().getKitchenTaskManager().addTask(recipes.get(5),
                    CatERing.getInstance().getUserManager().getCurrentUser(),
                    turn);

            System.out.println(tdl);

            //Delete so the database is clear
            CatERing.getInstance().getMenuManager().deleteMenu(m);
        }catch (Exception | CookUnavailableException e)
        {
            e.printStackTrace();
        }
    }
}
