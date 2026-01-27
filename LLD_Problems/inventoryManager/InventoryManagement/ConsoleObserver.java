package InventoryManagement;

public class ConsoleObserver implements Observer {
    public void update(String msg){
        System.out.println("--------------------------------");
        System.out.println(msg);
        System.out.println("--------------------------------");
    }
}
