import ParkingManagerPackage.ParkingFloor;
import ParkingManagerPackage.ParkingManager;
import ParkingManagerPackage.ParkingSpot;
import ParkingManagerPackage.SlotType;
import ParkingManagerPackage.Vehicle;
import ParkingManagerPackage.Car;
import ParkingManagerPackage.PaymentStrategy;
import ParkingManagerPackage.CashPayment;
import ParkingManagerPackage.Bike;
import ParkingManagerPackage.Ticket;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<ParkingSpot> spots = new ArrayList<>();
        for(int i = 1; i <= 1; i++) {
            spots.add(new ParkingSpot("S" + i, SlotType.CAR));
            spots.add(new ParkingSpot("B" + i, SlotType.BIKE));
        }
        List<ParkingSpot> spots2 = new ArrayList<>();
        for(int i = 1; i <= 1; i++) {
            spots2.add(new ParkingSpot("S" + i, SlotType.CAR));
            spots2.add(new ParkingSpot("B" + i, SlotType.BIKE));
        }

        ParkingFloor floor1 = new ParkingFloor("F1", spots);
        ParkingFloor floor2 = new ParkingFloor("F2", spots2);

        ParkingManager parkingManager = ParkingManager.getInstance();
        parkingManager.loadParkingFloor(floor1);
        parkingManager.loadParkingFloor(floor2);

        PaymentStrategy paymentStrategy = new CashPayment();

        Vehicle car1 = new Car("KA-01-HH-1234");
        Vehicle car2 = new Car("KB-01-HH-1234");
        Vehicle car3 = new Car("KC-01-HH-1234");

        Vehicle bike1 = new Bike("KA-01-HH-9999");
        Vehicle bike2 = new Bike("KB-01-HH-9999");
        Vehicle bike3 = new Bike("KC-01-HH-9999");

        Ticket t1 = parkingManager.enter(car1);
        Ticket t2 = parkingManager.enter(bike1);
        Ticket t3 =parkingManager.enter(car2);
        Ticket t4 = parkingManager.enter(bike2);

        Ticket t5 = parkingManager.enter(bike3);  // no spot available
        Ticket t6 = parkingManager.enter(car3); // no spot available

        int f1 = parkingManager.getParkingFee(t1); 
        parkingManager.pay(t1,paymentStrategy,f1);

        parkingManager.pay(t2, paymentStrategy, parkingManager.getParkingFee(t2)-10); // insufficient payment

        Ticket t7 = parkingManager.enter(car3); 
        Ticket t8 = parkingManager.enter(bike3); 

    }
}