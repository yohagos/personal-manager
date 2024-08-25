package com.prototype.personal_manager.requests;

import com.prototype.personal_manager.entities.Location;
import com.prototype.personal_manager.enums.EmployeeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "E100")
    @NotEmpty(message = "E100")
    private String firstName;
    @NotBlank(message = "E101")
    @NotEmpty(message = "E101")
    private String lastName;
    @NotBlank(message = "E102")
    @NotEmpty(message = "E102")
    private String identification;
    @NotBlank(message = "E103")
    @NotEmpty(message = "E103")
    private String email;

    private Location address;
    @NotBlank(message = "E104")
    @NotEmpty(message = "E104")
    private EmployeeType type;
}
