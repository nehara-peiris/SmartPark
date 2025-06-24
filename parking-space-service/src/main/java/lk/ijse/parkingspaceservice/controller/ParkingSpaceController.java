package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService service;

    @PostMapping
    public ParkingSpace create(@RequestBody ParkingSpace parkingSpace) {
        return service.create(parkingSpace);
    }

    @GetMapping
    public List<ParkingSpace> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ParkingSpace> getById(@PathVariable String id) {
        return service.findById(id);
    }
}
