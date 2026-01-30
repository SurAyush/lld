package CarRentalSystem;

public class Car extends Vehicle {
    private String numberplate;
    private int noOfDoors;
    private boolean isAutomatic;

    public Car(String vehicleId, String make, String model, int year,
               double rentalPricePerDay, double rentalPricePerHour, double basePrice,
               String numberplate, int noOfDoors, boolean isAutomatic) {
        super(vehicleId, make, model, year, rentalPricePerDay, rentalPricePerHour, basePrice);
        this.numberplate = numberplate;
        this.noOfDoors = noOfDoors;
        this.isAutomatic = isAutomatic;
    }

    public String getNumberplate() {
        return numberplate;
    }
    public int getNoOfDoors() {
        return noOfDoors;
    }
    public boolean isAutomatic() {
        return isAutomatic;
    }
}
