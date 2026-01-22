package ParkingManagerPackage;

import java.time.Duration;
import java.time.LocalDateTime;

class StandardFeeCalculator implements FeeCalculator {
    private final int feePerHourCar, feePerHourBike, basepay;

    public StandardFeeCalculator(int feePerHourCar, int feePerHourBike, int basepay) {
        this.feePerHourBike = feePerHourBike;
        this.feePerHourCar = feePerHourCar;
        this.basepay = basepay;
    }

    @Override
    public int calculatePaymentAmount(Ticket ticket) {
        LocalDateTime exTime = LocalDateTime.now();
        LocalDateTime inTime = ticket.getInTime();
        Duration parkingDuration = Duration.between(inTime, exTime);
        
        long minutesParked = parkingDuration.toMinutes();
        long hoursParked = (minutesParked + 59) / 60; 

        if(ticket.getVehicle().getVehicleType() == VehicleType.BIKE) {
            return (int) (hoursParked * feePerHourBike) + basepay;
        } else if(ticket.getVehicle().getVehicleType() == VehicleType.CAR) {
            return (int) (hoursParked * feePerHourCar) + basepay;
        }
        else{
            return (int) (hoursParked * feePerHourCar) + basepay;
        }
    }
}
