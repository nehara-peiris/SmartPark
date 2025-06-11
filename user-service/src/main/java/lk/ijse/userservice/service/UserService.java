package lk.ijse.userservice.service;

import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repo.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository repo = new UserRepository();

    public User register(User user) {
        user.setId(UUID.randomUUID().toString());
        return repo.save(user);
    }

    public Optional<User> login(String email, String password) {
        return repo.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public Optional<User> getProfile(String id) {
        return repo.findById(id);
    }

    public User updateProfile(User user) {
        return repo.save(user);
    }
}
