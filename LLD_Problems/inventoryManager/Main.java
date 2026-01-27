import InventoryManagement.*;
import InventoryManagement.ElectronicsProduct.ElectronicsProductBuilder;
import InventoryManagement.Observer;
import InventoryManagement.ConsoleObserver;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = InventoryManager.getInstance();

        // Creating warehouses
        Warehouse warehouse1 = new Warehouse(1,"North Kolkata" );
        Warehouse warehouse2 = new Warehouse(2,"South Kolkata" );
        
        manager.addWarehouse(warehouse1);
        manager.addWarehouse(warehouse2);

        // Creating products
        Product p1 = new Product("Blue Lays Family Pack", 10, 3, new InTimeReplenish(3));
        Product p2 = new Product("Blue Lays Personal Pack", 50, 10, new BulkReplenish(50, 20));
        Product p3 = new Product("Coke 1L", 20, 5, new InTimeReplenish(5));

        // Adding products to warehouses
        manager.addProduct(warehouse1, p1);
        manager.addProduct(warehouse1, p2);
        manager.addProduct(warehouse1, p3);

        Product p4 = new ElectronicsProductBuilder("Iphone 17 8GB 256 GB", 2, 1, new InTimeReplenish(2))
                        .setBrand("Apple")
                        .setModel("Iphone 17")
                        .setWarrantyPeriod(24)
                        .setPowerUsage(20)
                        .build();

        manager.addProduct(warehouse2, p4);
        Product p5 = new Product("Coke 1L", 20, 5, new InTimeReplenish(5));
        manager.addProduct(warehouse2, p5);

        // Add observer
        Observer obs1 = new ConsoleObserver();
        manager.addObs(obs1);

        // Scanning inventory
        manager.scanInv();

        // Search
        HashMap<Warehouse, Product> map = manager.findProduct("Coke 1L");
        for(Warehouse w: map.keySet()){
            System.out.println("Warehouse ID: " + w.getId() + ", Location: " + w.getLocation());
            System.out.println(map.get(w));
        }

        // Simulating sales
        manager.sell(warehouse1, "Coke 1L", 16);
        manager.sell(warehouse1, "Blue Lays Personal Pack", 45);
        manager.sell(warehouse2, "Iphone 17 8GB 256 GB", 2);

        manager.sell(warehouse1, "Coke 1L", 5);

        manager.scanInv();
        manager.replenish();

        manager.sell(warehouse1, "Coke 1L", 6);

        manager.scanInv();
        manager.replenish();
    }    
}
