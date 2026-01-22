package ElevatorPackage;

public class ElevatorFactory {
    public static Elevator createElevator(String id, int speed, String algorithmType) {
        if(algorithmType.equals("SCAN")) {
            return new Elevator(id, speed, new SCAN());
        }
        else if (algorithmType.equals("FCFS")) {
            return new Elevator(id, speed, new FCFS());
        }
        else if(algorithmType.equals("SCAN+")) {
            return new Elevator(id, speed, new SCANPlus());
        }
        else{
            throw new IllegalArgumentException("Unknown algorithm type");
        }
    }
}
