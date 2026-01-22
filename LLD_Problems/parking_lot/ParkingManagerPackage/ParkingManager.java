package ParkingManagerPackage;
import java.util.List;
import java.util.ArrayList;

public class ParkingManager {
    private static ParkingManager instance;
    
    private List<ParkingFloor> parkingFloors;
    private FeeCalculator feeCalculator;
    private final List<Ticket> all_tickets;
    private int ticketCounter;
    private final int feePerHour; 

    private ParkingManager() {
        all_tickets = new ArrayList<>();
        ticketCounter = 0;
        feePerHour = 10;
        feeCalculator = new StandardFeeCalculator(feePerHour, feePerHour / 2, 10);
        parkingFloors = new ArrayList<>();
    }

    public static ParkingManager getInstance() {
        if (instance == null) {
            instance = new ParkingManager();
        }
        return instance;
    }

    public void loadParkingFloor(ParkingFloor floor) {
        this.parkingFloors.add(floor);
    }

    private SlotType getSlotType(Vehicle vehicle) {
        if(vehicle.getVehicleType() == VehicleType.BIKE) {
            return SlotType.BIKE;
        } else if(vehicle.getVehicleType() == VehicleType.CAR) {
            return SlotType.CAR;
        } 
        return SlotType.CAR;    // default
    }


    public Ticket enter(Vehicle vehicle) {
        SlotType slotType = getSlotType(vehicle);
        for (ParkingFloor floor : parkingFloors) {
            ParkingSpot spot = floor.findAvailableSpot(slotType);
            if (spot != null) {
                spot.occupySlot();
                Ticket ticket = new Ticket("TK" + ticketCounter++, vehicle, spot, floor);
                all_tickets.add(ticket);
                System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked at floor " + ticket.getParkingFloor().getFloorId() + " with ticket " + ticket.getTicketId());
                return ticket;
            }
        }
        System.out.println("No available spot for vehicle " + vehicle.getLicensePlate());
        return null; 
    }

    public int getParkingFee(Ticket ticket) {
        int amount = feeCalculator.calculatePaymentAmount(ticket);
        System.out.println("Calculated parking fee for " + ticket.getVehicle().getLicensePlate() + " is: " + amount);
        return amount;
    }

    public boolean pay(Ticket ticket, PaymentStrategy paymentStrategy, int amount) {
        int calculatedAmount = getParkingFee(ticket);
        if (calculatedAmount != amount) {
            System.out.println("Incorrect payment amount. Expected: " + calculatedAmount + ", Received: " + amount);
            return false; 
        }
        boolean paymentStatus = paymentStrategy.pay(amount);
        if (paymentStatus) {
            ticket.markExit(amount);
            ticket.getParkingSpot().vacateSlot();
            return true;
        }
        return false;
    }

}
