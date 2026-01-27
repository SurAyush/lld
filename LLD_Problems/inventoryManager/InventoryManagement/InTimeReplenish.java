package InventoryManagement;

public class InTimeReplenish implements Replenish {
    private int minReplenishLevel;

    public InTimeReplenish(int minReplenishLevel) {
        this.minReplenishLevel = minReplenishLevel;
    }

    @Override
    public int refill(Product product) {
        int total = product.getThreshold() - product.getQuantity() + minReplenishLevel;
        if (total <= 0) {
            return 0; 
        }
        return total;
    }
}
