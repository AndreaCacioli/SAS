package businesslogic.kitchentask;

import businesslogic.menu.MenuItem;
import businesslogic.recipe.Procedure;
import businesslogic.turn.Turn;
import businesslogic.user.User;

import java.time.Duration;

public class KitchenTask {
    private User cook;
    private Turn turn;
    private Procedure procedure;
    private Duration esteemTime;
    private Float amount;

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
        return "KitchenTask{" +
                "cook=" + cook +
                ", turn=" + turn +
                ", procedure=" + procedure +
                ", esteemTime=" + esteemTime +
                ", amount=" + amount +
                "}\n";
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public void setDuration(Duration esteemTime) {
        this.esteemTime = esteemTime;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
