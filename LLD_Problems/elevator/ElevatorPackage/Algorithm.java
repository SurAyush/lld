package ElevatorPackage;
import java.util.Deque;
import java.util.PriorityQueue;

interface Algorithm {
    void onRequest(Request request, int currentFloor, ElevatorState state);
    void onArrive(int floor);
    int getNextFloor(int currentFloor, ElevatorState state);
}
