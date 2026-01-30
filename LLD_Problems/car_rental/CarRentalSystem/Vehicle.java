package CarRentalSystem;

public class Vehicle {
    private String vehicleId, make, model;
    private int year;
    private double rentalPricePerDay, rentalPricePerHour, basePrice;

    public Vehicle(String vehicleId, String make, String model, int year,
                   double rentalPricePerDay, double rentalPricePerHour, double basePrice) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalPricePerDay = rentalPricePerDay;
        this.rentalPricePerHour = rentalPricePerHour;
        this.basePrice = basePrice;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
    public double getRentalPricePerHour() {
        return rentalPricePerHour;
    }
    public double getBasePrice() {
        return basePrice;
    }
    double setRentalPricePerDay(double rentalPricePerDay) {
        return this.rentalPricePerDay = rentalPricePerDay;
    }
    double setRentalPricePerHour(double rentalPricePerHour) {
        return this.rentalPricePerHour = rentalPricePerHour;
    }
    double setBasePrice(double basePrice) {
        return this.basePrice = basePrice;
    }
}
