package com.prototype.personal_manager.entities;

import com.prototype.personal_manager.common.BaseEntity;
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
@Table(name = "locations")
public class Location extends BaseEntity {
    private String cityName;
    private String streetName;
    private String streetNumber;
    private String zipCode;
    @OneToOne(mappedBy = "location", optional = true)
    private Facility facility;
    @OneToOne(mappedBy = "address", optional = true)
    private Employee employee;
}
