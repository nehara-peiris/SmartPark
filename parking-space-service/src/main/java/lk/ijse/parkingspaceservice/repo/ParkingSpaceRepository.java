package lk.ijse.parkingspaceservice.repo;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingSpaceRepository {
    private final Map<String, ParkingSpace> db = new ConcurrentHashMap<>();

    public ParkingSpace save(ParkingSpace space) {
        db.put(space.getId(), space);
        return space;
    }

    public List<ParkingSpace> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<ParkingSpace> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<ParkingSpace> filter(String zone, Boolean available) {
        return db.values().stream()
                .filter(space -> (zone == null || space.getZone().equalsIgnoreCase(zone)))
                .filter(space -> (available == null || space.isAvailable() == available))
                .collect(Collectors.toList());
    }
}