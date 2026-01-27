package InventoryManagement;

public class BulkReplenish implements Replenish {
    private int maxCapacity;
    private int bulkSize;

    public BulkReplenish(int maxCapacity, int bulkSize) {
        this.maxCapacity = maxCapacity;
        this.bulkSize = bulkSize;
    }

    @Override
    public int refill(Product product) {
        int spaceAvailable = maxCapacity - product.getQuantity();

        if (spaceAvailable <= 0) {
            return 0; 
        }

        // Refill in bulk but do not exceed max capacity
        return Math.min(bulkSize, spaceAvailable);
    }
}
