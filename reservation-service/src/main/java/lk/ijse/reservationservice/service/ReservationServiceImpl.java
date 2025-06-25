package lk.ijse.reservationservice.service;

import lk.ijse.reservationservice.entity.Reservation;
import lk.ijse.reservationservice.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Reservation> getByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Reservation getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reservation create(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
