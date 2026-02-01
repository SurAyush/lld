import VendingMachinePackage.*;
import java.util.*;
import VendingMachinePackage.Currency;

public class Main{
    public static void main(String[] args) {
        VendingMachine vm = VendingMachine.getInstance();
        
        vm.showStock();
        
        Product p1 = new Product(1,"Coke 300ML Can", 30);
        Product p2 = new Product(2,"Prime 300ML Can", 100);
        Product p3 = new Product(3,"Frooti 300ML Can", 20);
        Map<Currency, Integer> init_money = new HashMap<>();
        init_money.put(Currency.TEN,10);
        init_money.put(Currency.TWENTY,10);
        init_money.put(Currency.FIFTY,5);
        init_money.put(Currency.HUNDRED,5);
        CoinInventory ci = new CoinInventory(init_money);
        vm.updateInventory(ci);
        vm.showInventory();

        vm.fill(p1, 10);
        vm.fill(p2,5);
        vm.fill(p3, 7);

        vm.showStock();

        vm.insertMoney(Currency.HUNDRED);
        vm.selectItem(1);
        vm.selectItem(3);
        vm.selectItem(3);
        vm.selectItem(3);
        vm.dispense();
        Map<Currency, Integer> r = vm.refund();
        if(r!=null)
            for(Currency c: r.keySet())
                System.out.println(c + " : "+r.get(c));
            
        
        vm.showStock();

        vm.refill(3, 10);

        vm.insertMoney(Currency.TWENTY);
        vm.insertMoney(Currency.TWENTY);
        vm.selectItem(1);

        vm.showStock();

        r = vm.refund();
        if(r!=null)
            for(Currency c: r.keySet())
                System.out.println(c + " : "+r.get(c));
        
        vm.showStock();
        vm.showInventory();
    }
}