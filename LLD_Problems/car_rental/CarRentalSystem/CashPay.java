package CarRentalSystem;

public class CashPay implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using Cash");
        return true;
    }
    @Override
    public boolean refund(double amount) {
        System.out.println("Refunded " + amount + " using Cash");
        return true;
    }
    
}
