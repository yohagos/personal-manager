package com.prototype.personal_manager.services;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.entities.Employee;
import com.prototype.personal_manager.mappers.EmployeeMapper;
import com.prototype.personal_manager.repositories.EmployeeRepository;
import com.prototype.personal_manager.requests.EmployeeRequest;
import com.prototype.personal_manager.responses.EmployeeResponse;
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
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public PageResponse<EmployeeResponse> loadEmployees(
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Employee> employees = employeeRepository.findAllDisplayableEmployees(pageable);
        List<EmployeeResponse> employeeResponses = employees.stream()
                .map(employeeMapper::toEmployeeResponse)
                .toList();
        return new PageResponse<>(
                employeeResponses,
                employees.getNumber(),
                employees.getSize(),
                employees.getTotalElements(),
                employees.getTotalPages(),
                employees.isFirst(),
                employees.isLast()
        );
    }

    @Transactional
    public EmployeeResponse loadEmployeeById(UUID employeeId) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow();
        return employeeMapper.toEmployeeResponse(employee);
    }

    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        var optionalEmployeeByFirstName = employeeRepository.findByFirstName(request.getFirstName());
        var optionalEmployeeByLastName = employeeRepository.findByLastName(request.getLastName());
        var optionalEmployeeByEmail = employeeRepository.findByEmail(request.getEmail());

        if (optionalEmployeeByEmail.isPresent() ||
                (optionalEmployeeByFirstName.isPresent() && optionalEmployeeByLastName.isPresent()))
            throw new RuntimeException("Employee with email or fullname already exists");

        var employee = employeeMapper.toEmployee(request);
        employee =  employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }


    public EmployeeResponse updateEmployee(
            UUID employeeId,
            EmployeeRequest request
    ) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find an Employee by Id with " + employeeId));

        Optional.of(request.getFirstName())
                .filter(name -> !name.isEmpty() || !name.equals(employee.getFirstName()))
                .ifPresent(employee::setFirstName);
        Optional.of(request.getLastName())
                .filter(name -> !name.isEmpty() || !name.equals(employee.getLastName()))
                .ifPresent(employee::setLastName);
        Optional.of(request.getEmail())
                .filter(email -> !email.isEmpty() || !email.equals(employee.getEmail()))
                .ifPresent(employee::setEmail);
        Optional.of(request.getIdentification())
                .filter(identity -> !identity.isEmpty() || !identity.equals(employee.getIdentification()))
                .ifPresent(employee::setIdentification);
        Optional.of(request.getAddress())
                .filter(add -> !add.equals(employee.getAddress()))
                .ifPresent(employee::setAddress);
        Optional.of(request.getType())
                .map(typ -> !typ.equals(employee.getType()))
                .ifPresent(employee::setType);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }
}
