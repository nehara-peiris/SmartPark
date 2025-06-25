package lk.ijse.reservationservice.service;

import lk.ijse.reservationservice.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAll();
    List<Reservation> getByUserId(String userId);
    Reservation getById(String id);
    Reservation create(Reservation reservation);
    Reservation update(Reservation reservation);
    void delete(String id);
}
