package ParkingManagerPackage;

public class ParkingSpot {
    private String slotId;
    private SlotType slotType;
    private boolean isOccupied;

    public ParkingSpot(String slotId, SlotType slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySlot() {
        this.isOccupied = true;
    }

    public void vacateSlot() {
        this.isOccupied = false;
    }
}
