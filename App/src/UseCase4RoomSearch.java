import java.util.HashMap;
import java.util.Map;

class Rooms {
    private String type;
    private double price;

    public Rooms(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}

class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return availability;
    }
}

class RoomSearchService {
    public void searchAvailableRooms(
            RoomInventory inventory,
            Rooms singleRooms,
            Rooms doubleRooms,
            Rooms suiteRooms) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0) {
            System.out.println(singleRooms.getType() + " Rooms Available: " +
                    availability.get("Single") + " Price: " + singleRooms.getPrice());
        }

        if (availability.get("Double") > 0) {
            System.out.println(doubleRooms.getType() + " Rooms Available: " +
                    availability.get("Double") + " Price: " + doubleRooms.getPrice());
        }

        if (availability.get("Suite") > 0) {
            System.out.println(suiteRooms.getType() + " Rooms Available: " +
                    availability.get("Suite") + " Price: " + suiteRooms.getPrice());
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        Rooms singleRooms = new Rooms("Single", 100.0);
        Rooms doubleRooms = new Rooms("Double", 180.0);
        Rooms suiteRooms = new Rooms("Suite", 300.0);

        RoomInventory inventory = new RoomInventory();
        RoomSearchService service = new RoomSearchService();

        service.searchAvailableRooms(inventory, singleRooms, doubleRooms, suiteRooms);
    }
}