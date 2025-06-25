package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository repository;

    @Override
    public List<ParkingSpace> getAllSpaces() {
        return repository.findAll();
    }

    @Override
    public ParkingSpace save(ParkingSpace space) {
        if (space.getId() == null || space.getId().isBlank()) {
            space.setId(UUID.randomUUID().toString()); // 🔑 Generate ID if not set
        }
        return repository.save(space);
    }
    @Override
    public ParkingSpace update(ParkingSpace space) {
        return repository.save(space); // ID-based overwrite
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public ParkingSpace getById(String id) {
        return repository.findById(id).orElse(null);
    }
}
