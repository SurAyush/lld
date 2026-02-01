package VendingMachinePackage;

import java.util.Map;

class NoCoinState extends State {

    NoCoinState(VendingMachine vm){
        super(vm);
    }

    void insertMoney(Currency c){
        machine.addMoney(c.getVal());
        machine.getCoinInventory().add(c, 1);
        machine.setState(this.machine.hasCoinState);
    }

    void selectItem(int item_id){
        System.out.println("Cannot select in NoCoinState");
        return;
    }

    void dispense(){
        System.out.println("Cannot dispense in NoCoinState");
        return;
    };

    Map<Currency, Integer> refund(){
        System.out.println("Cannot refund in NoCoinState");
        return null;
    }

    void refill(int item_id, int count){
        if(count<=0){
            System.out.println("Minimum 1 product has to refilled");
            return;
        }
        if(!this.machine.inStock(item_id)){
            System.out.println("Product never found | Cannot refill");
            return;
        }
        this.machine.addStock(item_id, count);
    }

    void fill(Product p, int count){
        if(count<=0){
            System.out.println("Minimum 1 product has to refilled");
            return;
        }
        int id = p.getId();
        if(this.machine.inStock(id)){
            System.out.println("Productalready found | New product cannot have same id");
            return;
        }
        this.machine.addNew(p, count);
    }
}
