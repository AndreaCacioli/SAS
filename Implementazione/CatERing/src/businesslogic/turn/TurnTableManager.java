package businesslogic.turn;

public class TurnTableManager {
    private static TurnTable turnTable;

    static TurnTable getInstance()
    {
        turnTable = TurnTable.getInstance();
        return turnTable;
    }

    public TurnTable getTurnTable() {
        return TurnTable.getInstance();
    }
}
