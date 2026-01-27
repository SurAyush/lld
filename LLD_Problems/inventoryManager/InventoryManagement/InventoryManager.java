package InventoryManagement;
import java.util.*;

public class InventoryManager{
    static volatile InventoryManager instance;
    List<Warehouse> warehouses;
    List<Observer> observers;

    private InventoryManager(){
        warehouses = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static InventoryManager getInstance(){
        if(instance==null){
            synchronized(InventoryManager.class){
                if(instance==null){
                    instance = new InventoryManager();
                }
            }
        }
        return instance;
    }

    public void addWarehouse(Warehouse w){
        warehouses.add(w);
    }

    public boolean removeWarehouse(int wid){
        for(Warehouse w: warehouses){
            if(w.getId() == wid){
                warehouses.remove(w);
                return true;
            }
        }
        return false;
    }

    public void addObs(Observer obs){
        observers.add(obs);
    }

    public HashMap<Warehouse, Product> findProduct(String pid){
        HashMap<Warehouse, Product> map = new HashMap<>();
        for(Warehouse w: warehouses){
            Product p = w.findProduct(pid);
            if(p!=null)
                map.put(w,p);
        }
        return map;
    }

    public void addProduct(Warehouse w, Product p){
        Product existing = w.findProduct(p.getId());
        if(existing==null){
            w.addNewProduct(p);
        }
        else{
            w.addToProduct(existing.getId(), p.getQuantity());
        }
    }

    public void sell(Warehouse w, String pid, int quantity){
        Product p = w.findProduct(pid);
        if(p==null){
            throw new IllegalArgumentException("Product Not Found");
        }
        if(p.getQuantity()<quantity){
            System.out.println("Insufficient Stock for product " + pid + " in Warehouse ID: " + w.getId());
            return;
        }
        System.out.println("Sold " + quantity + " units of product " + pid + " from Warehouse ID: " + w.getId());
        p.remove(quantity);
    }

    public void scanInv(){
        for(Warehouse w: warehouses){
            List<Product> def = w.scanWarehouse();
            if(def.size()==0) 
                continue;
            String msg = "Warehouse with wid = " + w.getId() + " and location : " + w.getLocation()+"\nProduct Deficit List: \n" + def.toString(); 
            notifyObs(msg);
        }
    }

    public void replenish(){
        for(Warehouse w: warehouses){
            w.replenish();
        }
    }

    private void notifyObs(String msg){
        for(Observer obs: observers){
            obs.update(msg);
        }
    }

    public Warehouse getWarehouse(int wid){
        for(Warehouse w: warehouses){
            if(w.getId() == wid)
                return w;
        }
        return null;
    }

}