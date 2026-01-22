package ElevatorPackage;

import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SCAN implements Algorithm {

    
    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;

    SCAN(){
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>((a,b)-> b - a);   // max-heap for down direction
    }

    @Override
    public void onRequest(Request request, int currentFloor, ElevatorState state) {
        if(request.getFloor() < currentFloor){
            downQueue.add(request.getFloor());
        }
        else if(request.getFloor() > currentFloor){
            upQueue.add(request.getFloor());
        }
    }

    @Override
    public void onArrive(int floor) {
        while(!upQueue.isEmpty() && upQueue.peek() == floor){
            upQueue.poll();
        }
        while(!downQueue.isEmpty() && downQueue.peek() == floor){
            downQueue.poll();
        }
    }

    @Override
    public int getNextFloor(int currentFloor, ElevatorState state) {
        // Standard SCAN algorithm -- Disk Scheduling analogy -> Modified one (also called LOOK)
        // we will move in one direction until there are no more requests in that direction
        // then reverse direction
        // No need to go to the end if there are no requests  -- not being naive
        
        if(state == ElevatorState.UP){
            if(!upQueue.isEmpty()){
                return upQueue.peek();
            } 
            else if(!downQueue.isEmpty()){
                return downQueue.peek();
            }
        } 
        else if(state == ElevatorState.DOWN){
            if(!downQueue.isEmpty()){
                return downQueue.peek();
            } 
            else if(!upQueue.isEmpty()){
                return upQueue.peek();
            }
        }
        else if(state == ElevatorState.IDLE){
            // nearest request
            if(upQueue.isEmpty() && downQueue.isEmpty()){
                return currentFloor;
            }
            else if(upQueue.isEmpty()){
                return downQueue.peek();
            }
            else if(downQueue.isEmpty()){
                return upQueue.peek();
            }
            if(Math.abs(currentFloor - upQueue.peek()) < Math.abs(currentFloor - downQueue.peek())){
                return upQueue.peek();
            } else {
                return downQueue.peek();
            }
        }

        return currentFloor;
    }
}
