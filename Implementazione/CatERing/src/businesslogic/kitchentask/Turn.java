package businesslogic.kitchentask;

import businesslogic.user.User;

import java.util.ArrayList;

public class Turn {
    private ArrayList<User> availableCooks;

    public Turn()
    {
        availableCooks = new ArrayList<>();
    }

    public void addAvailability(User u)
    {
        if (u.isCook()) availableCooks.add(u);
        else System.err.println("The user is not a cook!");
    }
}
