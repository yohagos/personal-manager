package com.prototype.personal_manager.controllers;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.requests.EmployeeRequest;
import com.prototype.personal_manager.responses.EmployeeResponse;
import com.prototype.personal_manager.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeResponse>> getEmployees(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(employeeService.loadEmployees(page, size));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable(name = "employeeId") UUID employeeId
    ) {
        return ResponseEntity.ok(employeeService.loadEmployeeById(employeeId));
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeResponse> addEmployee(
            @RequestBody EmployeeRequest request
    ) {
        return ResponseEntity.ok(employeeService.saveEmployee(request));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable(name = "employeeId") UUID employeeId,
            @RequestBody EmployeeRequest request
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, request));
    }

}
