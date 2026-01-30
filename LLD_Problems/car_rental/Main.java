import CarRentalSystem.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        // ===== Setup Manager (Singleton) =====
        VehicleRentalManager manager = VehicleRentalManager.getInstance();

        // ===== Create Stores =====
        Store kolkataStore = new Store(1, 700001, City.KOLKATA, "Kolkata Central", "Park Street");
        Store bangaloreStore = new Store(2, 560001, City.BANGALORE, "Bangalore Hub", "MG Road");

        manager.addStore(kolkataStore);
        manager.addStore(bangaloreStore);

        // ===== Create Vehicles =====
        Vehicle car1 = new Car("V1", "Toyota", "Camry", 2022, 3000, 200, 500, "WB12AB1234", 4, true);
        Vehicle car2 = new Car("V2", "Honda", "City", 2021, 2500, 180, 400, "KA05CD5678", 4, false);

        kolkataStore.addVehicle(car1);
        bangaloreStore.addVehicle(car2);

        // ===== Create User =====
        User user = new User("Rahul", 101);

        // ===== Booking Duration =====
        BookingDuration duration = new BookingDuration(
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusDays(2)
        );

        // ===== Pricing + Payment Strategies =====
        PricingStrategy pricing = new Daily();
        PaymentStrategy payment = new CashPay();

        // ===== Search Available Vehicles =====
        System.out.println("\nAvailable cars in KOLKATA:");
        List<Vehicle> available = manager.get_available_vehicles(City.KOLKATA, duration);
        for (Vehicle v : available) {
            System.out.println(" - " + v.getVehicleId() + " " + v.getMake() + " " + v.getModel());
        }

        // ===== Reserve Vehicle =====
        Vehicle selectedCar = car1;
        double totalFee = manager.get_fee(selectedCar, duration, pricing);
        double lockingFee = manager.get_locking_fee(selectedCar, duration, pricing);

        System.out.println("\nTotal Fee: " + totalFee);
        System.out.println("Locking Fee (advance): " + lockingFee);

        UUID bookingId = manager.reserve(
                user,
                selectedCar,
                duration,
                payment,
                pricing,
                (int) lockingFee
        );

        System.out.println("\nReservation successful. Booking ID = " + bookingId);

        // ===== Checkout / Complete Booking =====
        double remaining = totalFee - lockingFee;
        System.out.println("\nPaying remaining amount: " + remaining);

        manager.completeRentalBooking(bookingId, payment, remaining);

        System.out.println("Booking completed successfully!");

        // ===== Try Cancelling (should fail if already completed) =====
        try {
            manager.cancelReservation(bookingId);
        } catch (Exception e) {
            System.out.println("\nCancel failed as expected: " + e.getMessage());
        }

        System.out.println("\n=== Test Run Completed ===");
    }
}
