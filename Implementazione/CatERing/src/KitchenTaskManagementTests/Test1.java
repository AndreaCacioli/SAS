package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.ServiceException;
import businesslogic.kitchentask.KitchenTask;
import businesslogic.kitchentask.ToDoList;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import businesslogic.turn.TurnTable;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.time.Duration;

public class Test1 {
    public static void main(String[] args) {

        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("TEST GENERA FOGLIO");

            //Get menu from service
            var service = ServiceInfo.loadServiceInfoForEvent(1, 2);
            ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().generateToDoList(service);

            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();

            System.out.println(tdl);

            System.out.println("TEST AGGIUNGI PROCEDURA");
            CatERing.getInstance().getKitchenTaskManager().addProcedure(recipes.get(5));
            System.out.println(tdl);


            System.out.println("TEST ORDINA LISTA");
            Comparator<KitchenTask> comparatorCookName = new Comparator<KitchenTask>() {
                public int compare(KitchenTask o1, KitchenTask o2) {
                    if (o1.getCooks().get(0) != null && o2.getCooks().get(0) != null) {
                        return o1.getCooks().get(0).getUserName().compareTo(o2.getCooks().get(0).getUserName());
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
            System.out.println(tdl);


            System.out.println("TEST AGGIUNGI LAVORO CUOCO x2");
            TurnTable tt = CatERing.getInstance().getKitchenTaskManager().getTurnTable();
            Turn turn = tt.getTurnById(1);
            CatERing.getInstance().getUserManager().fakeLogin("Marinella");
            ArrayList<User> cooks = new ArrayList<>();
            cooks.add(CatERing.getInstance().getUserManager().getCurrentUser());
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            //adding everything
            KitchenTask toUpdate = CatERing.getInstance().getKitchenTaskManager().addTask(recipes.get(8),
                    cooks,
                    turn);
            System.out.println(tdl);

            //only adding turn
            CatERing.getInstance().getKitchenTaskManager().addTask(recipes.get(1),
                    null,
                    tt.getRandomTurn());
            System.out.println(tdl);


            System.out.println("TEST ADD FEATURES");
            CatERing.getInstance().getKitchenTaskManager().addFeatures(toUpdate, Duration.ofMinutes(59), 0.5f);
            System.out.println(tdl);

            System.out.println("TEST REMOVE FEATURES");
            CatERing.getInstance().getKitchenTaskManager().addFeatures(toUpdate, Duration.ofMinutes(59), null);
            System.out.println(tdl);

        } catch (Exception | ServiceException e) {
            e.printStackTrace();
        }
    }
}