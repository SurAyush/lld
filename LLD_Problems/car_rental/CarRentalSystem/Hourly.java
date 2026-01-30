package CarRentalSystem;

public class Hourly implements PricingStrategy {
    @Override
    public double calculatePrice(BookingDuration rentalTime, Vehicle vehicle) {
        double price = rentalTime.toHours() * vehicle.getRentalPricePerHour();
        return price + vehicle.getBasePrice();
    }
}
