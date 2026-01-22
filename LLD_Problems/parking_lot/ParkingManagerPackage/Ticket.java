package ParkingManagerPackage;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingFloor parkingFloor;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Duration parkingDuration;
    private double fee;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot, ParkingFloor parkingFloor) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.parkingFloor = parkingFloor;
        this.entryTime = LocalDateTime.now();
    }

    public void markExit(int fee) {
        this.exitTime = LocalDateTime.now();
        this.parkingDuration = Duration.between(entryTime, exitTime);
        this.fee = fee;
    }

    public LocalDateTime getInTime() {
        return this.entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public ParkingFloor getParkingFloor() {
        return this.parkingFloor;
    }

    public ParkingSpot getSpot() {
        return this.parkingSpot;
    }

    public String getTicketId() {
        return this.ticketId;
    }
}

