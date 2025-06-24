package lk.ijse.userservice.controller;

import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> loggedIn = service.login(user.getEmail(), user.getPassword());
        if (loggedIn.isPresent()) {
            String token = JwtUtil.generateToken(loggedIn.get().getId(), loggedIn.get().getRole().name());
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "userId", loggedIn.get().getId()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    @GetMapping("/{id}")
    public Optional<User> getProfile(@PathVariable String id) {
        return service.getProfile(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return service.updateProfile(user);
    }
}
