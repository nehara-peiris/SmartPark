package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository repository;

    public ParkingSpace create(ParkingSpace parkingSpace) {
        parkingSpace.setId(UUID.randomUUID().toString());
        return repository.save(parkingSpace);
    }

    public List<ParkingSpace> findAll() {
        return repository.findAll();
    }

    public Optional<ParkingSpace> findById(String id) {
        return repository.findById(id);
    }
}
