package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService service;

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllSpaces(@RequestHeader("x-user-id") String userId) {
        return ResponseEntity.ok(service.getAllSpaces());
    }

    @PostMapping
    public ResponseEntity<ParkingSpace> create(@RequestBody ParkingSpace space,
                                               @RequestHeader("x-user-id") String userId) {
        space.setOwnerId(userId);
        return ResponseEntity.ok(service.save(space));
    }

    @PutMapping
    public ResponseEntity<ParkingSpace> update(@RequestBody ParkingSpace space,
                                               @RequestHeader("x-user-id") String userId) {
        space.setOwnerId(userId); // update with current user if needed
        return ResponseEntity.ok(service.update(space));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getById(@PathVariable String id,
                                                @RequestHeader("x-user-id") String userId) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id,
                                    @RequestHeader("x-user-id") String userId) {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
