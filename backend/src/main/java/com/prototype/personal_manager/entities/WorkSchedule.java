package com.prototype.personal_manager.entities;

import com.prototype.personal_manager.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_schedules")
public class WorkSchedule extends BaseEntity {
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employees_id")
    private Employee scheduledEmployee;
    private boolean sendOutSchedule;
    private boolean verifiedAttendance;
    private boolean loggedIn;
}
