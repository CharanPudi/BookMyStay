import java.util.*;

class Reservations {
    private String reservationId;
    private String roomType;

    public Reservations(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRooms(String roomType, int count) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + count);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void reserveRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public int getAvailableCount(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class RoomAllocationService {
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservations reservations, RoomInventory inventory) {
        String roomType = reservations.getRoomType();
        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }
        String roomId = generateRoomId(roomType);
        allocatedRoomIds.add(roomId);
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);
        inventory.reserveRoom(roomType);
        System.out.println("Reservation " + reservations.getReservationId() + " allocated room " + roomId);
    }

    private String generateRoomId(String roomType) {
        String roomId;
        do {
            roomId = roomType + "-" + UUID.randomUUID().toString().substring(0, 8);
        } while (allocatedRoomIds.contains(roomId));
        return roomId;
    }
}

public class UseCase6RoomAllocation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRooms("DELUXE", 2);
        inventory.addRooms("STANDARD", 1);

        Queue<Reservations> queue = new LinkedList<>();
        queue.add(new Reservations("R1", "DELUXE"));
        queue.add(new Reservations("R2", "DELUXE"));
        queue.add(new Reservations("R3", "DELUXE"));
        queue.add(new Reservations("R4", "STANDARD"));

        RoomAllocationService service = new RoomAllocationService();

        while (!queue.isEmpty()) {
            service.allocateRoom(queue.poll(), inventory);
        }
    }
}