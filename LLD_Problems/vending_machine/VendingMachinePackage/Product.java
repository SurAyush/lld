package VendingMachinePackage;
public class Product {
    private final int id;
    private final String name;
    private int cost;
    
    public Product(int id, String name, int cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId(){
        return id;
    }

    public int getCost(){
        return cost;
    }

    public String getName(){
        return name;
    }
}
