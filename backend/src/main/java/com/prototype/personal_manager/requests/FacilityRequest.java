package com.prototype.personal_manager.requests;

import com.prototype.personal_manager.entities.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityRequest {
    @NotBlank(message = "F100")
    @NotNull(message = "F100")
    private String facilityName;
    @NotBlank(message = "F101")
    @NotNull(message = "F101")
    private String managerFirstName;
    @NotBlank(message = "F102")
    @NotNull(message = "F102")
    private String managerLastName;
    private String assistentFirstName;
    private String assistentLastName;
    @NotBlank(message = "F103")
    @NotNull(message = "F103")
    private String facilityTelephoneNumber;
    @NotBlank(message = "F104")
    @NotNull(message = "F104")
    private Location location;
}
