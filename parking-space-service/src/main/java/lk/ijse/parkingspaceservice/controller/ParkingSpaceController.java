package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/spaces")
public class ParkingSpaceController {

    private final ParkingSpaceService service = new ParkingSpaceService();

    @PostMapping
    public ParkingSpace addSpace(@RequestBody ParkingSpace space) {
        return service.addSpace(space);
    }

    @GetMapping
    public List<ParkingSpace> listSpaces(
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) Boolean available
    ) {
        return service.getSpaces(zone, available);
    }

    @PostMapping("/reserve")
    public ResponseEntity<ParkingSpace> reserve(@RequestParam String id) {
        return service.reserve(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(400).body(null));
    }

    @PostMapping("/release")
    public ResponseEntity<ParkingSpace> release(@RequestParam String id) {
        return service.release(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ParkingSpace> updateStatus(@PathVariable String id,
                                                     @RequestParam boolean available,
                                                     @RequestParam boolean reserved) {
        return service.updateStatus(id, available, reserved)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }
}