package ParkingManagerPackage;
import java.util.List;

public class ParkingFloor {
    List<ParkingSpot> parkingSpots;
    private String floorId;
    
    public ParkingFloor(String floorId, List<ParkingSpot> parkingSpots) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
    }

    public String getFloorId() {
        return floorId;
    }

    public ParkingSpot findAvailableSpot(SlotType slotType) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSlotType() == slotType && !spot.isOccupied()) {
                return spot;
            }
        }
        return null; // No available spot found
    }
}
