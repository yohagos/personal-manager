package com.prototype.personal_manager.requests;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkScheduleRequest {
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private boolean verifiedAttendance;
    private UUID scheduledEmployeeId;
    private boolean loggedIn;
    private boolean sendOutSchedule;
}
