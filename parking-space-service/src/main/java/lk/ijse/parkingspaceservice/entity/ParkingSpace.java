package lk.ijse.parkingspaceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpace {
    private String id;
    private String location;
    private boolean available;
    private boolean reserved;
    private String zone;
    private String ownerId;

}
