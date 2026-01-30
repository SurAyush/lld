package CarRentalSystem;
import java.time.Duration;
import java.util.*;

public class VehicleRentalManager {
    private static volatile VehicleRentalManager instance;
    private Map<Integer, Store> stores;
    private Map<UUID, Booking> rentals;
    private Map<String, List<Booking>> bookings;
    private double locking_rate;        // can use strategy pattern if needed


    private VehicleRentalManager() {
        stores = new HashMap<>();
        rentals = new HashMap<>();
        bookings = new HashMap<>();
        locking_rate = 0.1;       // Default locking rate
    }

    public static VehicleRentalManager getInstance() {
        if (instance == null) {
            synchronized (VehicleRentalManager.class) {
                if (instance == null) {
                    instance = new VehicleRentalManager();
                }
            }
        }
        return instance;
    }

    public double getLocking_rate() {
        return locking_rate;
    }
    void setLocking_rate(double locking_rate) {
        this.locking_rate = locking_rate;
    }

    public List<Vehicle> get_available_vehicles(City city, BookingDuration bd){
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Store store : stores.values()) {
            if (store.getCity() == city) {
                List<Vehicle> vehicles = store.getVehicles();
                for (Vehicle vehicle : vehicles) {
                    if(isavailable(vehicle, bd)) {
                        availableVehicles.add(vehicle);
                    }
                }
            }
        }
        return availableVehicles;
    }

    public List<Vehicle> get_available_vehicles(BookingDuration bd){
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Store store : stores.values()) {
            List<Vehicle> vehicles = store.getVehicles();
            for (Vehicle vehicle : vehicles) {
                if(isavailable(vehicle, bd)) {
                    availableVehicles.add(vehicle);
                }
            }
        
        }
        return availableVehicles;
    }

    private boolean isavailable(Vehicle vehicle, BookingDuration bd) {
        String v_id = vehicle.getVehicleId();
        if(!bookings.containsKey(v_id))
            return true;
        List<Booking> vehicleBookings = bookings.get(v_id);
        for(Booking booking : vehicleBookings) {
            if(bd.overlaps(booking.getDuration()))
                return false;
        }
        return true;
    }

    public double get_fee(Vehicle vehicle, BookingDuration bd, PricingStrategy ps) {
        return ps.calculatePrice(bd, vehicle);
    }

    public double get_locking_fee(Vehicle vehicle, BookingDuration bd, PricingStrategy ps) {
        return locking_rate * get_fee(vehicle, bd, ps);
    }

    public void addStore(Store store) {
        stores.put(store.getStoreId(), store);
    }

    public UUID reserve(User user, Vehicle vehicle, BookingDuration bd, PaymentStrategy paymentStrategy, PricingStrategy pricingStrategy, int amountPaid) {
        if(!isavailable(vehicle, bd)) {
            throw new IllegalArgumentException("Vehicle is not available for the given duration");
        }
        double lockingFee = get_locking_fee(vehicle, bd, pricingStrategy);
        if(amountPaid < lockingFee) {
            throw new IllegalArgumentException("Insufficient amount paid for locking the vehicle");
        }
        UUID uuid = UUID.randomUUID();
        if(!paymentStrategy.pay(amountPaid)){
            throw new IllegalArgumentException("Payment failed");
        }
        
        Store store = getStoreByVehicle(vehicle);
        Booking booking = new Booking(vehicle, user, bd, store, lockingFee, pricingStrategy, paymentStrategy);
        booking.setStatus(Status.PAID);
        String v_id = vehicle.getVehicleId();
        bookings.putIfAbsent(v_id, new ArrayList<>());
        bookings.get(v_id).add(booking);

        rentals.put(uuid, booking);

        return uuid;
    }

    private Store getStoreByVehicle(Vehicle vehicle) {
        for (Store store : stores.values()) {
            if (store.containsVehicle(vehicle.getVehicleId())) {  
                return store;
            }
        }
        throw new IllegalArgumentException("Vehicle does not belong to any store");
    }

    public void cancelReservation(UUID bookingID) {
        if(!rentals.containsKey(bookingID)) {
            throw new IllegalArgumentException("Invalid booking ID");
        }
        Booking booking = rentals.get(bookingID);
        if(booking.getStatus() == Status.NOT_PAID) {
            throw new IllegalArgumentException("Only PAID bookings can be cancelled");
        }
        if(booking.getStatus() == Status.CANCELLED) {
            throw new IllegalArgumentException("Booking is already cancelled");
        }
        if(booking.getStatus() == Status.COMPLETED) {
            throw new IllegalArgumentException("Completed bookings cannot be cancelled");
        }
        double lockingFee = booking.getLockingFeePaid();
        PaymentStrategy paymentStrategy = booking.getPaymentStrategy();
        if(!paymentStrategy.refund(lockingFee)){
            throw new IllegalArgumentException("Refund failed");
        }
        String v_id = booking.getVehicle().getVehicleId();
        bookings.get(v_id).remove(booking);
        booking.setStatus(Status.CANCELLED);
        rentals.remove(bookingID);
    }

    public void completeRentalBooking(UUID bookingID, PaymentStrategy paymentStrategy, double amount) {
        if(!rentals.containsKey(bookingID)) {
            throw new IllegalArgumentException("Invalid booking ID");
        }
        Booking booking = rentals.get(bookingID);
        booking.setStatus(Status.COMPLETED);
        double paymentLeft = booking.getPricingStrategy().calculatePrice(booking.getDuration(), booking.getVehicle()) - booking.getLockingFeePaid();
        if(paymentLeft>amount){
            throw new IllegalArgumentException("Insufficient amount paid to complete the booking");
        }
        double extra = amount - paymentLeft;
        if(!paymentStrategy.pay(amount)){
            throw new IllegalArgumentException("Payment failed");
        }
        paymentStrategy.refund(extra);        
    }

}
