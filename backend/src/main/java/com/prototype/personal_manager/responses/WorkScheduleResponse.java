package com.prototype.personal_manager.responses;

import com.prototype.personal_manager.entities.Employee;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkScheduleResponse {
    private UUID id;
    private Employee scheduledEmployee;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private boolean loggedIn;
    private boolean verifiedAttendance;
    private boolean sendOutSchedule;
}
