package CarRentalSystem;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Store {
    private int store_id;
    private int pincode;
    private City city;
    private String name;
    private String address;
    private Map<String, Vehicle> vehicles;

    public Store(int store_id, int pincode, City city, String name, String address) {
        this.store_id = store_id;
        this.pincode = pincode;
        this.city = city;
        this.name = name;
        this.address = address;
        this.vehicles = new HashMap<>();
    }

    public int getStoreId() {
        return store_id;
    }
    public int getPincode() {
        return pincode;
    }
    public City getCity() {
        return city;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles.values());
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
    }
    public void removeVehicle(String vehicleId) {
        vehicles.remove(vehicleId);
    }

    boolean containsVehicle(String vehicleId) {
        return vehicles.containsKey(vehicleId);
    }
}
