package com.prototype.personal_manager.entities;

import com.prototype.personal_manager.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "facilities")
public class Facility extends BaseEntity {
    private String facilityName;
    private String managerFirstName;
    private String managerLastName;
    private String assistentFirstName;
    private String assistentLastName;
    private String facilityTelephoneNumber;
    private String city;
    private String streetName;
    private String streetNumber;
    private String zipCode;

}
