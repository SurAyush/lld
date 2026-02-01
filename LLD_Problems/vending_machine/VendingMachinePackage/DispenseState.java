package VendingMachinePackage;

import java.util.Map;

class DispenseState extends State {

    DispenseState(VendingMachine vm){
        super(vm);
    }

    void insertMoney(Currency c){
        System.out.println("Cannot add more money now");
        return;
    }

    void selectItem(int item_id){
        if(!machine.inStock(item_id)){
            System.out.println("Item out of stock, get something else or refund");    
            return;
        }
        int cost = machine.getItem(item_id).getCost();
        if(cost > machine.getMoney()){
            System.out.println("Insufficient Coins");
            return;
        }
        machine.addToBuffer(item_id);
        machine.deductMoney(cost);
        return;
    }

    void dispense(){
        // make sure change is available
        int money = machine.getMoney();
        ExchangeStrategy s = machine.getExchangeStrategy();
        if(!s.isPossible(machine.getCoinInventory(), money)){
            System.out.println("Exchange not available! Sorry!");
            return;
        }
        machine.dispenseBufferItems();
        if(machine.getMoney()>0){
            machine.setState(machine.hasCoinState);
        }
        else{
            machine.setState(machine.noCoinState);
        }
    };

    Map<Currency, Integer> refund(){
        // return coins
        int coins = machine.getMoney();         // unselected
        machine.deductMoney(coins);
        coins += machine.clearBuffer();        // selected
        ExchangeStrategy s = machine.getExchangeStrategy();
        System.out.println("Refunded "+ coins);
        machine.setState(machine.noCoinState);
        CoinInventory ci = machine.getCoinInventory();
        Map<Currency, Integer> r = s.exchange(ci, coins);
        if(r!=null){
            for(Currency c: r.keySet()){
                ci.remove(c, r.get(c));
            }
        }
        
        return r;
    }

    void refill(int item_id, int count){
        System.out.println("Cannot refill in DispenseState");
    }

    void fill(Product p, int count){
        System.out.println("Cannot fill in DispenseState");
    }
}
