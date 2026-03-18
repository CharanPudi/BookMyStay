import java.util.HashMap;
import java.util.Map;

public class UseCase3InventorySetup {

    private Map<String, Integer> roomAvailability;

    public UseCase3InventorySetup() {
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 20);
        roomAvailability.put("Suite", 5);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public static void main(String[] args) {
        UseCase3InventorySetup inventory = new UseCase3InventorySetup();

        System.out.println("Initial Availability:");
        System.out.println(inventory.getRoomAvailability());

        inventory.updateAvailability("Single", 8);
        inventory.updateAvailability("Suite", 3);

        System.out.println("Updated Availability:");
        System.out.println(inventory.getRoomAvailability());
    }
}