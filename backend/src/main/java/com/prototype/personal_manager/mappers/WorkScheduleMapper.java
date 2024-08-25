package com.prototype.personal_manager.mappers;

import com.prototype.personal_manager.entities.WorkSchedule;
import com.prototype.personal_manager.repositories.EmployeeRepository;
import com.prototype.personal_manager.requests.WorkScheduleRequest;
import com.prototype.personal_manager.responses.WorkScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkScheduleMapper {

    private final EmployeeRepository employeeRepository;

    public WorkScheduleResponse toWorkScheduleResponse(
            WorkSchedule entity
    ) {
        return WorkScheduleResponse.builder()
                .id(entity.getId())
                .shiftStart(entity.getShiftStart())
                .shiftEnd(entity.getShiftEnd())
                .scheduledEmployee(entity.getScheduledEmployee())
                .verifiedAttendance(entity.isVerifiedAttendance())
                .loggedIn(entity.isLoggedIn())
                .sendOutSchedule(entity.isSendOutSchedule())
                .build();
    }

    public WorkSchedule toWorkSchedule(
            WorkScheduleRequest request
    ) {
        var employee = employeeRepository.findById(request.getScheduledEmployeeId()).orElseThrow();
        return WorkSchedule.builder()
                .shiftStart(request.getShiftStart())
                .shiftEnd(request.getShiftEnd())
                .scheduledEmployee(employee)
                .sendOutSchedule(request.isSendOutSchedule())
                .loggedIn(request.isLoggedIn())
                .verifiedAttendance(request.isVerifiedAttendance())
                .build();
    }
}
