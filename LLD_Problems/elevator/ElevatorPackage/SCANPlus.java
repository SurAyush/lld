package ElevatorPackage;
import java.util.PriorityQueue;

public class SCANPlus implements Algorithm {

    PriorityQueue<Request> upQueue;
    PriorityQueue<Request> downQueue;
    
    SCANPlus() {
        upQueue = new PriorityQueue<>((a, b) -> a.getFloor() - b.getFloor());
        downQueue = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());
    }

    @Override
    public void onRequest(Request request, int currentFloor, ElevatorState state) {
        if(request.getFloor() < currentFloor){
            downQueue.add(request);
        }
        else if(request.getFloor() > currentFloor){
            upQueue.add(request);
        }
    }

    @Override
    public void onArrive(int floor) {
        while(!upQueue.isEmpty() && upQueue.peek().getFloor() == floor){
            upQueue.poll();
        }
        while(!downQueue.isEmpty() && downQueue.peek().getFloor() == floor){
            downQueue.poll();
        }
    }

    @Override
    public int getNextFloor(int currentFloor, ElevatorState state) {
        // Standard SCAN algorithm with additional logic to only pick External Reuqest aligned with current direction
        // SCAN + Direction
        if(state == ElevatorState.UP){
            if(!upQueue.isEmpty()){
                while(upQueue.peek() instanceof ExternalRequest && ((ExternalRequest)upQueue.peek()).getDirection() == Direction.DOWN){
                    Request skippedRequest = upQueue.poll();
                    // re-add to downQueue
                    downQueue.add(skippedRequest);
                    if(upQueue.isEmpty()){
                        break;
                    }
                }
                if(!upQueue.isEmpty()){
                    return upQueue.peek().getFloor();
                }
            } 
            else if(!downQueue.isEmpty()){
                return downQueue.peek().getFloor();
            }
        } 
        else if(state == ElevatorState.DOWN){
            if(!downQueue.isEmpty()){
                while(downQueue.peek() instanceof ExternalRequest && ((ExternalRequest)downQueue.peek()).getDirection() == Direction.UP){
                    Request skippedRequest = downQueue.poll();
                    // re-add to upQueue
                    upQueue.add(skippedRequest);
                    if(downQueue.isEmpty()){
                        break;
                    }
                }
                if(!downQueue.isEmpty()){
                    return downQueue.peek().getFloor();
                }
            } 
            else if(!upQueue.isEmpty()){
                return upQueue.peek().getFloor();
            }
        }
        else if(state == ElevatorState.IDLE){
            if(upQueue.isEmpty() && downQueue.isEmpty()){
                return currentFloor;
            }
            else if(upQueue.isEmpty()){
                return downQueue.peek().getFloor();
            }
            else if(downQueue.isEmpty()){
                return upQueue.peek().getFloor();
            }
            if(Math.abs(currentFloor - upQueue.peek().getFloor()) < Math.abs(currentFloor - downQueue.peek().getFloor())){
                return upQueue.peek().getFloor();
            } else {
                return downQueue.peek().getFloor();
            }
        }

        return currentFloor;
    }
}
