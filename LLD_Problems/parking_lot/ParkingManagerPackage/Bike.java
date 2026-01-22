package ParkingManagerPackage;

public class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate);
    }

    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
}
