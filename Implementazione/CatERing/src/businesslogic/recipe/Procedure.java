package businesslogic.recipe;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Procedure {
    protected String name;

    public int getDataBaseId()
    {
        final Integer[] id = {-1};
        PersistenceManager.executeQuery("SELECT id FROM Recipes.id WHERE Recipes.name = " + name, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                id[0] = rs.getInt("id");
            }
        });
        return id[0];
    }

    public String getName() {
        return name;
    }
}
