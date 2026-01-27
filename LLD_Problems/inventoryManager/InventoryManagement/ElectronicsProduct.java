package InventoryManagement;

public class ElectronicsProduct extends Product{
    private final String brand;
    private final String model;
    private final int warrantyPeriod; // in months
    private final int powerUsage; // in watts

    private ElectronicsProduct(ElectronicsProductBuilder builder){  
        super(builder.pid, builder.quantity, builder.threshold, builder.strategy);
        this.brand = builder.brand;
        this.model = builder.model;
        this.warrantyPeriod = builder.warrantyPeriod;
        this.powerUsage = builder.powerUsage;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public int getWarrantyPeriod(){
        return warrantyPeriod;
    }

    public int getPowerUsage(){
        return powerUsage;
    }

    public static class ElectronicsProductBuilder{
        private String pid;
        private int quantity;
        private int threshold;
        private Replenish strategy;
        private String brand;
        private String model;
        private int warrantyPeriod;
        private int powerUsage;

        public ElectronicsProductBuilder(String pid, int quantity, int threshold, Replenish strategy){
            this.pid = pid;
            this.quantity = quantity;
            this.threshold = threshold;
            this.strategy = strategy;
        }

        public ElectronicsProductBuilder setBrand(String brand){
            this.brand = brand;
            return this;
        }

        public ElectronicsProductBuilder setModel(String model){
            this.model = model;
            return this;
        }

        public ElectronicsProductBuilder setWarrantyPeriod(int warrantyPeriod){
            this.warrantyPeriod = warrantyPeriod;
            return this;
        }

        public ElectronicsProductBuilder setPowerUsage(int powerUsage){
            this.powerUsage = powerUsage;
            return this;
        }

        public ElectronicsProduct build(){
            return new ElectronicsProduct(this);
        }
    }


}