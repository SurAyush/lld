package VendingMachinePackage;
import java.util.Map;

abstract class State {
    VendingMachine machine;

    State(VendingMachine machine){
        this.machine = machine;
    }

    abstract void insertMoney(Currency c);
    abstract void selectItem(int item_id);
    abstract void dispense();
    abstract Map<Currency, Integer> refund();
    abstract void refill(int item_id, int count);
    abstract void fill(Product p, int count);
}