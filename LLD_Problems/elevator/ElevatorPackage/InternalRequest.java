package ElevatorPackage;

public class InternalRequest implements Request {
    private int destinationFloor;
    private Elevator elevator;
    
    public InternalRequest(int destinationFloor, Elevator elevator) {
        this.destinationFloor = destinationFloor;
        this.elevator = elevator;
    }

    @Override
    public void execute() {
        elevator.addRequest(this);
    }

    @Override
    public int getFloor() {
        return destinationFloor;
    }
}
