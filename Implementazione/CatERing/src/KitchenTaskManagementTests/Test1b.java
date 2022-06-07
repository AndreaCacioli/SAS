package KitchenTaskManagementTests;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchentask.ServiceException;
import businesslogic.kitchentask.ToDoList;

public class Test1b {
    public static void main(String[] args) throws UseCaseLogicException, ServiceException {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        var service = ServiceInfo.loadServiceInfoForEvent(1, 2);
        ToDoList tdl = CatERing.getInstance().getKitchenTaskManager().emptyToDoList(service);
        System.out.println(tdl);

    }
}
