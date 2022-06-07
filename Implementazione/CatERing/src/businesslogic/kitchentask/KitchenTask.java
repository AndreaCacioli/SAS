package businesslogic.kitchentask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Procedure;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

public class KitchenTask {
    private ArrayList<User> cooks;
    private Turn turn;
    private Procedure procedure;

    public KitchenTask(ArrayList<User> cooks, Turn turn, Procedure procedure, Duration esteemTime, Float amount, int id) {
        this.cooks = cooks;
        this.turn = turn;
        this.procedure = procedure;
        this.esteemTime = esteemTime;
        this.amount = amount;
        this.id = id;
    }

    private Duration esteemTime;
    private Float amount;
    private int id;

    public KitchenTask(MenuItem item) {
        this.procedure = item.getItemRecipe();
    }

    public KitchenTask(Procedure procedure) {
        this.procedure = procedure;
    }

    public static void updateTask(KitchenTask kitchenTask) {
        deleteTask(kitchenTask);
        saveTask(kitchenTask, false);
    }

    public static void deleteTask(KitchenTask kitchenTask) {
        PersistenceManager.executeUpdate("DELETE FROM KitchenTasks WHERE id = " + kitchenTask.getId());
        PersistenceManager.executeUpdate("DELETE FROM CookTask WHERE idTask = " + kitchenTask.getId());

    }

    public ArrayList<User> getCooks() {
        return this.cooks;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    @Override
    public String toString() {
        return "KitchenTask # " + this.id +  "{" +
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
        if (cooks == null) {
            //This will never be done
            this.cooks = null;
            return;
        }
        if (this.turn == null && cooks.size() > 0) throw new UseCaseLogicException();
        this.cooks = new ArrayList<>(); //removing the previous list of cooks
        addCooks(cooks);
    }

    public void updateTask(Turn turn) {
        this.turn = turn;
        if (cooks != null && turn != null)
            cooks.removeIf(user -> !turn.isAvailable(user)); //Maybe change a variable in kitchenTaskManager to signal the drop of cooks
    }

    public Turn getTurn() {
        return turn;
    }

    public Duration getEsteemTime() {
        return esteemTime;
    }

    public Float getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<KitchenTask> getAllTasks() {
        ArrayList<KitchenTask> ret = new ArrayList<>();
        PersistenceManager.executeQuery("SELECT * " +
                "FROM KitchenTasks " +
                "LEFT JOIN Turns on (KitchenTasks.idTurn= Turns.id) " +
                "LEFT JOIN Recipes on (KitchenTasks.idProcedure = Recipes.id)", new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int idTask = rs.getInt("id");
                ArrayList<User> cooks = new ArrayList<>();
                PersistenceManager.executeQuery("SELECT * " +
                        "FROM CookTask JOIN Users on (CookTask.idCook = Users.id) " +
                        "WHERE CookTask.idTask = " + idTask, new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs1) throws SQLException {
                        User u = User.loadUserById(rs1.getInt("CookTask.idCook"));
                        cooks.add(u);
                    }
                });
                Turn turn = rs.getInt("idTurn") == 0 ? null : Turn.loadTurnById(rs.getInt("idTurn"));
                Procedure p = Recipe.loadRecipeById(rs.getInt("idProcedure"));
                String durationString = rs.getString("duration");
                Float amount = rs.getFloat("amount");
                KitchenTask kitchenTask = new KitchenTask(cooks.size() == 0 ? null : cooks, turn, p, durationString == null ? null : Duration.parse(durationString), amount == 0 ? null : amount, idTask);
                ret.add(kitchenTask);
            }
        });
        return ret;
    }

    private static int getMaxId() {
        final Integer[] max = new Integer[1];
        PersistenceManager.executeQuery("SELECT MAX(id) m From KitchenTasks;", new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                max[0] = rs.getInt("m");
            }
        });
        return max[0];
    }

    public static void saveTask(KitchenTask kitchenTask, boolean isNewTask) {
        String turn = kitchenTask.getTurn() == null ? "null" : String.valueOf(kitchenTask.getTurn().getId());
        String procedure = kitchenTask.getProcedure() == null ? "null" : String.valueOf(kitchenTask.getProcedure().getDataBaseId());
        String time = kitchenTask.getEsteemTime() == null ? "null" : "\"" + kitchenTask.getEsteemTime().toString() + "\"";

        if (isNewTask) {
            String newKitchenTaskUpdate = "INSERT INTO catering.KitchenTasks (idTurn, idProcedure, amount, duration)" +
                    " VALUES (" + turn +
                    ", " + procedure +
                    ", " + kitchenTask.getAmount() +
                    ", " + time +
                    ");";
            PersistenceManager.executeUpdate(newKitchenTaskUpdate);
            kitchenTask.id = getMaxId();
            ToDoList.saveNewTask(CatERing.getInstance().getKitchenTaskManager().getCurrentToDoList(), kitchenTask);
        } else {
            String newKitchenTaskUpdate = "INSERT INTO catering.KitchenTasks (id, idTurn, idProcedure, amount, duration)" +
                    " VALUES (" + kitchenTask.getId() +
                    ", " + turn +
                    ", " + procedure +
                    ", " + kitchenTask.getAmount() +
                    ", " + time +
                    ");";
            PersistenceManager.executeUpdate(newKitchenTaskUpdate);
        }

        //We assume the user is a cook, otherwise would have not come this far
        if (kitchenTask.cooks != null) {
            for (User u : kitchenTask.cooks) {
                String insertionCookTask = "INSERT INTO CookTask (idCook, idTask) VALUES " +
                        "(" + u.getId() +
                        ", " + kitchenTask.getId() +
                        ");";
                PersistenceManager.executeUpdate(insertionCookTask);

            }
        }
    }


}
