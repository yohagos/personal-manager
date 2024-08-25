package com.prototype.personal_manager.entities;

import com.prototype.personal_manager.common.BaseEntity;
import com.prototype.personal_manager.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location address;
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    public void setType(Boolean aBoolean) {
    }
}
