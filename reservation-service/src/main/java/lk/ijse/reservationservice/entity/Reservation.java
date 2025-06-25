package lk.ijse.reservationservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reservation {

    @Id
    private String id;

    private String userId;
    private String vehicleId;
    private String parkingSpaceId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean active;
}
