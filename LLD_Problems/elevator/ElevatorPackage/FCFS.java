package ElevatorPackage;

import java.util.Queue;
import java.util.LinkedList;

public class FCFS implements Algorithm {

    private Queue<Integer> requestQueue;

    FCFS() {
        this.requestQueue = new LinkedList<>();
    }

    @Override
    public void onRequest(Request request, int currentFloor, ElevatorState state) {
        requestQueue.add(request.getFloor());
    }

    @Override
    public void onArrive(int floor) {
        // remove all the requests for the arrived floor (might be multiple requests for same floor)
        while(requestQueue.contains(floor)) {
            requestQueue.remove(floor);
        }
    }

    @Override
    public int getNextFloor(int currentFloor, ElevatorState state) {
        return requestQueue.peek() != null ? requestQueue.peek() : currentFloor;
    }
}
