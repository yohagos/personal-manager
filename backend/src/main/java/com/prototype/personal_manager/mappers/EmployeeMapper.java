package com.prototype.personal_manager.mappers;

import com.prototype.personal_manager.entities.Employee;
import com.prototype.personal_manager.requests.EmployeeRequest;
import com.prototype.personal_manager.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {

    public EmployeeResponse toEmployeeResponse(
            Employee employee
    ) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .identification(employee.getIdentification())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .type(employee.getType())
                .build();
    }

    public Employee toEmployee(
            EmployeeRequest request
    ) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .identification(request.getIdentification())
                .email(request.getEmail())
                .address(request.getAddress())
                .type(request.getType())
                .build();
    }
}
