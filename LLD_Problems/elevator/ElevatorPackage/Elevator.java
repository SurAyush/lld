package ElevatorPackage;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class Elevator {
    private final String id;
    private int speed;
    private int currentFloor;
    private ElevatorState state;
    private Algorithm algorithm;
    private List<Observer> observers;

    Elevator(String id, int speed, Algorithm algorithm) {
        this.id = id;
        this.speed = speed;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.algorithm = algorithm;
    }

    void addRequest(Request request) {
        algorithm.onRequest(request, currentFloor, state);
    }

    private void arrived(int floor){
        opendoors();
        algorithm.onArrive(floor);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public int getFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public void step(){
        int nextfloor = algorithm.getNextFloor(currentFloor, state);
        
        if(nextfloor > currentFloor){
            state = ElevatorState.UP;
            currentFloor++;
            
            if(currentFloor == nextfloor){
                arrived(currentFloor);
            }
        } else if (nextfloor < currentFloor){
            state = ElevatorState.DOWN;
            currentFloor--;
            
            if(currentFloor == nextfloor){
                arrived(currentFloor);
            }
        } else {
            state = ElevatorState.IDLE;
        }

        notifyObservers();
    }
    
    private void opendoors(){
        // internal method to open doors
        System.out.println("Elevator " + id + " opening doors at floor " + currentFloor);
    }
}
