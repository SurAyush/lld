package ParkingManagerPackage;

abstract public class Vehicle {
    private final String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    abstract public VehicleType getVehicleType();
}