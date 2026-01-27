package InventoryManagement;
import java.util.*;

public class Warehouse {
    private List<Product> products;
    private int wid;
    private String location;

    public Warehouse(int wid, String location){
        this.wid = wid;
        this.location = location;
        this.products = new ArrayList<>();
    }
    
    boolean addNewProduct(Product newProduct){
        // merge to initial if exists
        for(Product p: products){
            if(p.getId().equals(newProduct.getId())){
                return false;
            }
        }
        products.add(newProduct);
        return true;
    }

    boolean addToProduct(String pid, int q){
        for(Product p: products){
            if(p.getId().equals(pid)){
                p.add(q);
                return true;
            }
        }
        return false;
    }

    List<Product> scanWarehouse(){
        List<Product> deficit = new ArrayList<>();
        for(Product p: products){
            if(p.getQuantity()<p.getThreshold()){
                deficit.add(p);    
            }
        }
        return deficit;
    }

    void replenish(){
        for(Product p: products){

            if(p.getQuantity() < p.getThreshold()){
                p.replenish();
            }
        }
    }

    Product findProduct(String pid){
        for(Product p: products){
            if(p.getId().equals(pid))
                return p;
        }
        return null;
    }

    public int getId(){
        return this.wid;
    }

    public String getLocation(){
        return this.location;
    }
}
