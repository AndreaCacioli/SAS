package businesslogic.kitchentask;

import businesslogic.UseCaseLogicException;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Procedure;
import businesslogic.turn.Turn;
import businesslogic.user.User;

import java.time.Duration;
import java.util.ArrayList;

public class KitchenTask {
    private ArrayList<User> cooks;
    private Turn turn;
    private Procedure procedure;
    private Duration esteemTime;
    private Float amount;
    //TODO Aggiungere la equals e la proprietá id sul database
    private int id;

    public KitchenTask(MenuItem item) {
        this.procedure = item.getItemRecipe();
    }

    public KitchenTask(Procedure procedure) {
        this.procedure = procedure;
    }

    public ArrayList<User> getCooks() {
        return this.cooks;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    @Override
    public String toString() {
        return "KitchenTask{" +
                "cook=" + cooks +
                ", turn=" + turn +
                ", procedure=" + procedure +
                ", esteemTime=" + esteemTime +
                ", amount=" + amount +
                "}\n";
    }

    public void addCooks(ArrayList<User> cooks) {
        if (turn == null) System.err.println("Adding cooks without turn");
        if (this.cooks == null) this.cooks = new ArrayList<>();
        for (User u : cooks) {
            if (!u.isCook() || !turn.isAvailable(u))
                System.err.println("Cook Unavailable"); //Maybe change a variable in kitchenTaskManager instead
            else this.cooks.add(u);
        }
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

    public void updateTask(Procedure procedure) {
        this.procedure = procedure;
    }

    public void updateTask(ArrayList<User> cooks) throws UseCaseLogicException {
        if(cooks == null)
        {
            this.cooks = null;
            return;
        }
        if(this.turn == null  && cooks.size() > 0) throw new UseCaseLogicException();
        this.cooks = new ArrayList<>(); //removing the previous list of cooks
        addCooks(cooks);
    }

    public void updateTask(Turn turn) {
        this.turn = turn;
        if(cooks != null && turn != null) cooks.removeIf(user -> !turn.isAvailable(user)); //Maybe change a variable in kitchenTaskManager to signal the drop of cooks
    }



}
