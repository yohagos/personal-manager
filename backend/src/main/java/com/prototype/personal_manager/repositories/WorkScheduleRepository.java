package com.prototype.personal_manager.repositories;

import com.prototype.personal_manager.entities.Facility;
import com.prototype.personal_manager.entities.WorkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, UUID> {

    @Query("""
            SELECT ws
            FROM WorkSchedule ws
            """)
    Page<WorkSchedule> findAllDisplayableWorkSchedules(Pageable pageable);
}
