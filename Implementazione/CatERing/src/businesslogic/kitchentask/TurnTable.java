package businesslogic.kitchentask;

import java.util.ArrayList;

public class TurnTable {
    private TurnTable instance;
    private ArrayList<Turn> turns;

    private TurnTable() {}

    public TurnTable getInstance() {
        if(instance == null) { instance = new TurnTable();}
        return instance;
    }
}
