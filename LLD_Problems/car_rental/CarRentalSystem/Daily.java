package CarRentalSystem;

public class Daily implements PricingStrategy {
    @Override
    public double calculatePrice(BookingDuration rentalTime, Vehicle vehicle) {
        double price = rentalTime.toDays() * vehicle.getRentalPricePerDay();
        if(rentalTime.toDays() > 7) {
            price *= 0.9; 
        }
        return price + vehicle.getBasePrice();
    }
}
