package CarRentalSystem;

public class Booking {
    private Vehicle vehicle;
    private User user;
    private BookingDuration duration;
    private Status status;
    private Store store;
    private double locking_fee_paid;
    private PricingStrategy pd;
    private PaymentStrategy paymentStrategy;

    public Booking(Vehicle vehicle, User user, BookingDuration duration, Store store, double locking_fee_paid, PricingStrategy pd, PaymentStrategy paymentStrategy) {
        this.vehicle = vehicle;
        this.user = user;
        this.duration = duration;
        this.status = Status.NOT_PAID;
        this.store = store;
        this.locking_fee_paid = locking_fee_paid;
        this.pd = pd;
        this.paymentStrategy = paymentStrategy;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public User getUser() {
        return user;
    }
    public BookingDuration getDuration() {
        return duration;
    }
    public Status getStatus() {
        return status;
    }
    public Store getStore() {
        return store;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public double getLockingFeePaid() {
        return locking_fee_paid;
    }
    public PricingStrategy getPricingStrategy(){
        return pd;
    }
    public PaymentStrategy getPaymentStrategy(){
        return paymentStrategy;
    }

}
