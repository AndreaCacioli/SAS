package businesslogic.kitchentask;

import businesslogic.menu.MenuItem;
import businesslogic.recipe.Procedure;
import businesslogic.user.User;

public class KitchenTask {
    private User cook;
    private Turn turn;
    private Procedure procedure;

    public KitchenTask(MenuItem item) {
        procedure = item.getItemRecipe();
    }
}
