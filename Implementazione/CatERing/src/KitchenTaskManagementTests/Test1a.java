package KitchenTaskManagementTests;

import businesslogic.CatERing;

public class Test1a {
    public static void main(String[] args) {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        //TODO implement openToDoList(Service)

        //TODO implement emptyToDoList(Service)
    }
}
