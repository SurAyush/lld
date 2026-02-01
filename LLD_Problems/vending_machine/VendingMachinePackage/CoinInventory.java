package VendingMachinePackage;
import java.util.Map;
import java.util.HashMap;

public class CoinInventory {
    private Map<Currency, Integer> content;

    public CoinInventory(){
        content = new HashMap<>();
    }

    public CoinInventory(Map<Currency, Integer> content){
        this.content = content;
    }

    void add(Currency c, int f){
        if(content.containsKey(c))
            content.put(c, content.get(c)+f);
        else 
            content.put(c,f);
    }

    void remove(Currency c, int f){
        if(!content.containsKey(c))
            throw new IllegalArgumentException("Insuffient currency of " + c);
        if(content.get(c)<f)
            throw new IllegalArgumentException("Insuffient currency of " + c);
        content.put(c, content.get(c)-f);
    }

    Map<Currency, Integer> getInv(){
        return new HashMap<>(content);
    }

    void show(){
        System.out.println("Coin Inventory");
        for(Currency c: content.keySet()){
            if(content.get(c)>0){
                System.out.println(c + " : " + content.get(c));
            }
        }
        System.out.println("------------------------------------------------");
    }
}
