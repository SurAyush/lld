package VendingMachinePackage;
import java.util.*;

public class VendingMachine {
    private static VendingMachine instance; 
    State noCoinState, hasCoinState, dispenseState;         // package protected
    private State currentState; 
    private final Map<Product,Integer> freq_map;
    private final Map<Integer,Product> product_map;
    private List<Integer> buffer;
    private int money_stored;
    private CoinInventory inventory;
    private ExchangeStrategy strategy;
    
    
    private VendingMachine(){
        freq_map = new HashMap<>();
        product_map = new HashMap<>();
        buffer = new ArrayList<>();
        money_stored = 0;
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        dispenseState = new DispenseState(this);
        currentState = noCoinState;
        inventory = new CoinInventory();
        strategy = new SimpleExchange();
    }

    public static VendingMachine getInstance(){
        if(instance!=null)
            return instance;
        instance = new VendingMachine();
        return instance;
    }

    public void insertMoney(Currency c){
        currentState.insertMoney(c);
    }

    public void selectItem(int id){
        currentState.selectItem(id);
    }

    public void dispense(){
        currentState.dispense();
    }

    public Map<Currency, Integer> refund(){
        return currentState.refund();
    }

    public void refill(int pid, int f){
        currentState.refill(pid, f);
    }

    public void fill(Product p, int f){
        currentState.fill(p, f);
    }

    public void updateInventory(CoinInventory ci){
        inventory = ci;
    }

    public void setStrategy(ExchangeStrategy es){
        strategy = es;
    }

    public void showStock(){
        System.out.println("Current Stock");
        for(Integer id: product_map.keySet()){
            Product p = product_map.get(id);
            if(freq_map.getOrDefault(p, 0)>0){
                System.out.println(p.getName() + " : " + freq_map.get(p));
            }
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void showInventory(){
        inventory.show();
    }

    CoinInventory getCoinInventory(){
        return inventory;
    }

    ExchangeStrategy getExchangeStrategy(){
        return strategy;
    }

    void setState(State s){
        currentState = s;
    }

    int getMoney(){
        return money_stored;
    }

    void deductMoney(int money){
        money_stored -= money;
    }

    void addMoney(int money){
        money_stored += money;
    }

    boolean inStock(int id){
        if(!product_map.containsKey(id)){
            return false;
        }
        if (freq_map.getOrDefault(product_map.get(id),0)>0)
            return true;
        return false;
    }

    Product getItem(int id){
        return product_map.getOrDefault(id, null);
    }

    void addToBuffer(int id){
        if(inStock(id)){
            buffer.add(id);
            freq_map.put(product_map.get(id), freq_map.get(product_map.get(id))-1);
        }
    }

    int clearBuffer(){
        int refund = 0;
        for(int p_id: buffer){
            Product p = product_map.get(p_id);
            freq_map.put(p, freq_map.get(p)+1);
            refund += p.getCost();
        }
        buffer.clear();
        return refund;
    }

    void dispenseBufferItems(){
        for(int p_id: buffer){
            Product p = product_map.get(p_id);
            System.out.println("Dispensing "+ p.getName());
        }
        buffer.clear();
    }

    void addStock(int id, int freq){
        Product p = product_map.get(id);
        if(p!=null){
            freq_map.put(p,freq_map.get(p)+freq);
            System.out.println("Stock Updated");
        }
        return;
    }

    void addNew(Product p, int count){
        if(product_map.containsKey(p.getId())){
            return;
        }
        product_map.put(p.getId(), p);
        freq_map.put(p, count);
        System.out.println("New Item Added To Stock");
    }
    
}
