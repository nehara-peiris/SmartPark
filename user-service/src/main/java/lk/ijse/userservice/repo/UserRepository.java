package lk.ijse.userservice.repo;

import lk.ijse.userservice.entity.User;

import java.util.*;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
