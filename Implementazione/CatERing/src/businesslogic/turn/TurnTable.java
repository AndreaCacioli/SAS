package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TurnTable {
    private static TurnTable instance;
    private ArrayList<Turn> turns;

    private TurnTable() {
        turns = new ArrayList<>();
        PersistenceManager.executeQuery("SELECT * FROM Turns", new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
               turns.add(new Turn(rs.getInt("id")));
            }
        });
    }

    public static TurnTable getInstance() {
        if(instance == null) { instance = new TurnTable();}
        return instance;
    }


    public Turn getRandomTurn() {
        Random r = new Random();
        return turns.get(r.nextInt(turns.size()));
    }

    public Turn getTurnById(int id)
    {
        for(Turn t : turns)
        {
            if(t.id == id) return t;
        }
        return null;
    }
}
