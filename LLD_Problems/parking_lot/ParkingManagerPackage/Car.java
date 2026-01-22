package ParkingManagerPackage;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
