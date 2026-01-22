package ParkingManagerPackage;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(int amount) {
        System.out.println("Processing cash payment of " + amount);
        // Additional logic for handling cash payment can be added here
        return true;
    }
    
}
