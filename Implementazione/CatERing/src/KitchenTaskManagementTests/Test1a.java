package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.ToDoList;

public class Test1a {
    public static void main(String[] args) throws UseCaseLogicException {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        var service = ServiceInfo.loadServiceInfoForEvent(1, 2);
        ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().openToDoList(service);
        System.out.println(tdl);

    }
}
