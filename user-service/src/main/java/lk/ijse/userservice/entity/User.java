package lk.ijse.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role; // "USER" or "OWNER"
}
