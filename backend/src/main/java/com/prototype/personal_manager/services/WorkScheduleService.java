package com.prototype.personal_manager.services;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.entities.WorkSchedule;
import com.prototype.personal_manager.mappers.WorkScheduleMapper;
import com.prototype.personal_manager.repositories.EmployeeRepository;
import com.prototype.personal_manager.repositories.WorkScheduleRepository;
import com.prototype.personal_manager.requests.WorkScheduleRequest;
import com.prototype.personal_manager.responses.WorkScheduleResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;
    private final WorkScheduleMapper workScheduleMapper;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public PageResponse<WorkScheduleResponse> loadWorkSchedules(
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<WorkSchedule> schedules = workScheduleRepository.findAllDisplayableWorkSchedules(pageable);
        List<WorkScheduleResponse> scheduleResponses = schedules.stream()
                .map(workScheduleMapper::toWorkScheduleResponse)
                .toList();
        return new PageResponse<>(
                scheduleResponses,
                schedules.getNumber(),
                schedules.getSize(),
                schedules.getTotalElements(),
                schedules.getTotalPages(),
                schedules.isFirst(),
                schedules.isLast()
        );
    }

    @Transactional
    public WorkScheduleResponse loadWorkScheduleById(UUID scheduleId) {
        var schedule = workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find WorkSchedule by Id "+scheduleId));
        return workScheduleMapper.toWorkScheduleResponse(schedule);
    }

    public WorkScheduleResponse saveWorkSchedule(WorkScheduleRequest request) {
        var schedule = workScheduleMapper.toWorkSchedule(request);
        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(schedule));
    }

    public WorkScheduleResponse updateWorkSchedule(
            UUID scheduleId,
            WorkScheduleRequest request
    ) {
        var schedule = workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find WorkSchedule by Id "+scheduleId));
        var employee = employeeRepository.findById(request.getScheduledEmployeeId()).orElseThrow();

        Optional.of(employee)
                .filter(emp -> !emp.equals(schedule.getScheduledEmployee()))
                .ifPresent(schedule::setScheduledEmployee);
        Optional.of(request.getShiftStart())
                .filter(shift -> !shift.isEqual(schedule.getShiftStart()))
                .ifPresent(schedule::setShiftStart);
        Optional.of(request.getShiftEnd())
                .filter(shift -> !shift.isEqual(schedule.getShiftEnd()))
                .ifPresent(schedule::setShiftEnd);
        Optional.of(request.isSendOutSchedule())
                .filter(out -> !out.equals(schedule.isSendOutSchedule()))
                .ifPresent(schedule::setSendOutSchedule);
        Optional.of(request.isLoggedIn())
                .filter(log -> !log.equals(schedule.isLoggedIn()))
                .ifPresent(schedule::setLoggedIn);
        Optional.of(request.isVerifiedAttendance())
                .filter(att -> !att.equals(schedule.isVerifiedAttendance()))
                .ifPresent(schedule::setVerifiedAttendance);
        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(schedule));
    }
}
