package lk.ijse.reservationservice.controller;

import lk.ijse.reservationservice.entity.Reservation;
import lk.ijse.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll(@RequestHeader("x-user-id") String userId) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Reservation>> getByUser(@RequestHeader("x-user-id") String userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable String id,
                                               @RequestHeader("x-user-id") String userId) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation,
                                              @RequestHeader("x-user-id") String userId,
                                              @RequestHeader("Authorization") String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Validate Vehicle
        try {
            ResponseEntity<Map> vehicleResponse = restTemplate.exchange(
                    "http://vehicle-service/api/vehicles/" + reservation.getVehicleId(),
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            if (!vehicleResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            String ownerId = (String) vehicleResponse.getBody().get("userId");
            if (!userId.equals(ownerId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Validate Parking Space
        try {
            ResponseEntity<Map> spaceResponse = restTemplate.exchange(
                    "http://parking-space-service/api/spaces/" + reservation.getParkingSpaceId(),
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            if (!spaceResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean available = (Boolean) spaceResponse.getBody().get("available");
            if (available == null || !available) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        reservation.setUserId(userId);
        return ResponseEntity.ok(service.create(reservation));
    }

    @PutMapping
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation,
                                              @RequestHeader("x-user-id") String userId,
                                              @RequestHeader("Authorization") String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Validate Vehicle
        try {
            ResponseEntity<Map> vehicleResponse = restTemplate.exchange(
                    "http://vehicle-service/api/vehicles/" + reservation.getVehicleId(),
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            if (!vehicleResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            String ownerId = (String) vehicleResponse.getBody().get("userId");
            if (!userId.equals(ownerId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Validate Parking Space
        try {
            ResponseEntity<Map> spaceResponse = restTemplate.exchange(
                    "http://parking-space-service/api/spaces/" + reservation.getParkingSpaceId(),
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            if (!spaceResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean available = (Boolean) spaceResponse.getBody().get("available");
            if (available == null || !available) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        reservation.setUserId(userId);
        return ResponseEntity.ok(service.update(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id,
                                    @RequestHeader("x-user-id") String userId) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
