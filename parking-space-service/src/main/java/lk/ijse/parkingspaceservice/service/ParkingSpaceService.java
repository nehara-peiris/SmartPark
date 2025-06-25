package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;

import java.util.List;

public interface ParkingSpaceService {
    List<ParkingSpace> getAllSpaces();
    ParkingSpace save(ParkingSpace space);
    ParkingSpace update(ParkingSpace space);
    void deleteById(String id);
    ParkingSpace getById(String id);
}
