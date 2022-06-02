package businesslogic.kitchentask;

import businesslogic.menu.MenuItem;
import businesslogic.recipe.Procedure;
import businesslogic.turn.Turn;
import businesslogic.user.User;

public class KitchenTask {
    private User cook;
    private Turn turn;
    private Procedure procedure;

    public KitchenTask(MenuItem item) {
        this.procedure = item.getItemRecipe();
    }

    public KitchenTask(Procedure procedure) {
        this.procedure = procedure;
    }

    public User getCook() {
        return cook;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    @Override
    public String toString() {
        return "\tTask{" +
                "cook=" + cook +
                ", turn=" + turn +
                ", procedure=" + procedure +
                "}\n";
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
