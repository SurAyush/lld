package VendingMachinePackage;
import java.util.Map;

class HasCoinState extends State {

    HasCoinState(VendingMachine vm){
        super(vm);
    }

    void insertMoney(Currency c){
        machine.addMoney(c.getVal());
        machine.getCoinInventory().add(c, 1);
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
        machine.setState(machine.dispenseState);
        return;
    }

    void dispense(){
        System.out.println("Cannot dispense in HasCoinState");
        return;
    };

    Map<Currency, Integer> refund(){
        // return Money -- no item selected
        ExchangeStrategy strategy = machine.getExchangeStrategy();
        int money = machine.getMoney();
        machine.deductMoney(machine.getMoney());
        machine.setState(machine.noCoinState);
        CoinInventory ci = machine.getCoinInventory();
        Map<Currency, Integer> r =  strategy.exchange(ci, money);
        if(r!=null){
            for(Currency c: r.keySet()){
                ci.remove(c, r.get(c));
            }
        }
        return r;
    }

    void refill(int item_id, int count){
        System.out.println("Cannot refill in HasCoinState");
    }

    void fill(Product p, int count){
        System.out.println("Cannot fill in HasCoinState");
    }
}
