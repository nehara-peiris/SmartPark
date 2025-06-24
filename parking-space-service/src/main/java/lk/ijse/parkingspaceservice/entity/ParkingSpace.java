package lk.ijse.parkingspaceservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ParkingSpace {
    @Id
    private String id;
    private String location;
    private boolean available;
    private boolean reserved;
    private String zone;
    private String ownerId;

}
