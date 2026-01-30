package CarRentalSystem;

public interface PricingStrategy {
    double calculatePrice(BookingDuration duration, Vehicle vehicle);
}
