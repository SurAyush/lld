package ElevatorPackage;

public class FloorObserver implements Observer {
    public void update(Elevator elevator) {
        System.out.println("Elevator " + elevator.getId() + " has reached floor " + elevator.getFloor() + " and is now " + elevator.getState());
    }
}
