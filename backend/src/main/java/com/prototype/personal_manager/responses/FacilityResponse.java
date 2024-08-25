package com.prototype.personal_manager.responses;

import com.prototype.personal_manager.entities.Location;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityResponse {
    private UUID id;
    private String facilityName;
    private String managerFirstName;
    private String managerLastName;
    private String assistentFirstName;
    private String assistentLastName;
    private String facilityTelephoneNumber;
    private Location location;
}
