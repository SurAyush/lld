import ElevatorPackage.*;
import ElevatorPackage.Observer;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        // let's create an elevator
        Elevator elevator = ElevatorFactory.createElevator("E1", 1, "SCAN+"); 
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(elevator);
        ElevatorManager manager = new ElevatorManager(elevators);
        
        Observer obs = new FloorObserver();
        elevator.addObserver(obs);

        // Simulate some elevator operations
        Request req1 = new ExternalRequest(6, Direction.DOWN, elevator);
        Request req2 = new ExternalRequest(3, Direction.UP, elevator);
        Request req3 = new InternalRequest(9, elevator);
        Request req4 = new InternalRequest(4, elevator);

        req1.execute();
        req2.execute();

        for(int i=0;i<3;i++){
            elevator.step();
        }

        req3.execute(); 
        
        for(int i=0;i<10;i++){
            elevator.step();
        }

        req4.execute();
        for(int i=0;i<5;i++){
            elevator.step();
        }

        // SCAN+
        // Elevator E1 has reached floor 1 and is now UP
        // Elevator E1 has reached floor 2 and is now UP
        // Elevator E1 opening doors at floor 3
        // Elevator E1 has reached floor 3 and is now UP
        // Elevator E1 has reached floor 4 and is now UP
        // Elevator E1 has reached floor 5 and is now UP
        // Elevator E1 has reached floor 6 and is now UP
        // Elevator E1 has reached floor 7 and is now UP
        // Elevator E1 has reached floor 8 and is now UP
        // Elevator E1 opening doors at floor 9
        // Elevator E1 has reached floor 9 and is now UP
        // Elevator E1 has reached floor 8 and is now DOWN
        // Elevator E1 has reached floor 7 and is now DOWN
        // Elevator E1 opening doors at floor 6
        // Elevator E1 has reached floor 6 and is now DOWN
        // Elevator E1 has reached floor 6 and is now IDLE
        // Elevator E1 has reached floor 5 and is now DOWN
        // Elevator E1 opening doors at floor 4
        // Elevator E1 has reached floor 4 and is now DOWN
        // Elevator E1 has reached floor 4 and is now IDLE
        // Elevator E1 has reached floor 4 and is now IDLE
        // Elevator E1 has reached floor 4 and is now IDLE
    }
}