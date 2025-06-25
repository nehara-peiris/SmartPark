package lk.ijse.reservationservice.controller;

import lk.ijse.reservationservice.entity.Reservation;
import lk.ijse.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

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
                                              @RequestHeader("x-user-id") String userId) {
        reservation.setUserId(userId);
        return ResponseEntity.ok(service.create(reservation));
    }

    @PutMapping
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation,
                                              @RequestHeader("x-user-id") String userId) {
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
