package com.prototype.personal_manager.controllers;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.requests.WorkScheduleRequest;
import com.prototype.personal_manager.responses.WorkScheduleResponse;
import com.prototype.personal_manager.services.WorkScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Tag(name = "Work Schedules")
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @GetMapping
    public ResponseEntity<PageResponse<WorkScheduleResponse>> getWorkSchedules(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(workScheduleService.loadWorkSchedules(page, size));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleResponse> getWorkScheduleById(
            @PathVariable(name = "scheduleId") UUID scheduleId
    ) {
        return ResponseEntity.ok(workScheduleService.loadWorkScheduleById(scheduleId));
    }

    @PostMapping("/add")
    public ResponseEntity<WorkScheduleResponse> addWorkScheduleById(
            @RequestBody WorkScheduleRequest request
    ) {
        return ResponseEntity.ok(workScheduleService.saveWorkSchedule(request));
    }

    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<WorkScheduleResponse> updateWorkSchedule(
            @PathVariable(name = "scheduleId") UUID scheduleId,
            @RequestBody WorkScheduleRequest request
    ) {
        return ResponseEntity.ok(workScheduleService.updateWorkSchedule(scheduleId, request));
    }
}
