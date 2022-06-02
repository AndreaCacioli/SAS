package businesslogic.turn;

import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Turn {
    private ArrayList<Integer> availableCooksIds;
    int id;

    public Turn(int id)
    {
        this.id = id;
        availableCooksIds = new ArrayList<>();
        //Ask db for availabilities
        PersistenceManager.executeQuery("Select * FROM Availability WHERE idTurn = " + id, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                availableCooksIds.add(rs.getInt("idUser"));
            }
        });

    }

    public void addAvailability(User u)
    {
        if (u.isCook()) availableCooksIds.add(u.getId());
        else System.err.println("The user is not a cook!");
    }

    public boolean isAvailable(User cook) {
        for(Integer i : availableCooksIds)
        {
            if(i == cook.getId()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Turn#" + id;
    }
}
