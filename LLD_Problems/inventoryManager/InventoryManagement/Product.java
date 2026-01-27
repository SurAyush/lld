package InventoryManagement;

public class Product {
    private String pid;
    private int quantity;
    private int threshold;
    private Replenish strategy;

    public Product(String pid, int quantity, int threshold, Replenish rep){
        this.pid = pid;
        this.quantity = quantity;
        this.threshold = threshold;
        this.strategy = rep;
    }

    void add(int quantity){
        this.quantity += quantity; 
        System.out.println("Replenished " + quantity + " units of Product ID: " + pid);
    }

    void remove(int quantity){
        if(this.quantity < quantity)
            throw new IllegalArgumentException("Insufficient Quantity");
        this.quantity -= quantity;
    }

    void replenish(){
        add(strategy.refill(this));
    }

    String getId(){
        return pid;
    }

    int getQuantity(){
        return quantity;
    }

    int getThreshold(){
        return threshold;
    }

    void setThreshold(int threshold){
        this.threshold = threshold;
    }

    Replenish getStrategy(){
        return strategy;
    }

    void setStrategy(Replenish newStrategy){
        this.strategy = newStrategy;
    }

    @Override
    public String toString(){
        return "Product ID: " + pid + ", Quantity: " + quantity + ", Threshold: " + threshold + "\n";
    }

}
