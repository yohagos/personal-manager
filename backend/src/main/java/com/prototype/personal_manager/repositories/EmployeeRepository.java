package com.prototype.personal_manager.repositories;

import com.prototype.personal_manager.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("""
            Select emp
            From Employee emp
            """)
    Page<Employee> findAllDisplayableEmployees(Pageable pageable);

    Optional<Employee> findByFirstName(String firstName);
    Optional<Employee> findByLastName(String lastName);
    Optional<Employee> findByEmail(String email);
}
