package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingSpaceService {
    private final ParkingSpaceRepository repo = new ParkingSpaceRepository();

    public ParkingSpace addSpace(ParkingSpace space) {
        space.setId(UUID.randomUUID().toString());
        space.setAvailable(true);
        space.setReserved(false);
        return repo.save(space);
    }

    public List<ParkingSpace> getSpaces(String zone, Boolean available) {
        return repo.filter(zone, available);
    }

    public Optional<ParkingSpace> reserve(String id) {
        return repo.findById(id).map(space -> {
            if (space.isAvailable() && !space.isReserved()) {
                space.setReserved(true);
                space.setAvailable(false);
                repo.save(space);
                return space;
            }
            return null;
        });
    }

    public Optional<ParkingSpace> release(String id) {
        return repo.findById(id).map(space -> {
            space.setReserved(false);
            space.setAvailable(true);
            return repo.save(space);
        });
    }

    public Optional<ParkingSpace> updateStatus(String id, boolean available, boolean reserved) {
        return repo.findById(id).map(space -> {
            space.setAvailable(available);
            space.setReserved(reserved);
            return repo.save(space);
        });
    }
}