package com.prototype.personal_manager.responses;

import com.prototype.personal_manager.entities.Location;
import com.prototype.personal_manager.enums.EmployeeType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    private Location address;
    private EmployeeType type;
}
