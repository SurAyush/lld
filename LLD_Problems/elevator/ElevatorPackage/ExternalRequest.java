package ElevatorPackage;

public class ExternalRequest implements Request  {
    private int requestedFloor;
    private Direction direction;
    private Elevator elevator;
    
    public ExternalRequest(int requestedFloor, Direction direction, Elevator elevator) {
        this.requestedFloor = requestedFloor;
        this.direction = direction;
        this.elevator = elevator;
    }

    @Override
    public void execute() {
        elevator.addRequest(this);
    }

    @Override
    public int getFloor() {
        return requestedFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
