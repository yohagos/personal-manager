package com.prototype.personal_manager.mappers;

import com.prototype.personal_manager.entities.Facility;
import com.prototype.personal_manager.requests.FacilityRequest;
import com.prototype.personal_manager.responses.FacilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityMapper {

    public FacilityResponse toFacilityResponse(
            Facility facility
    ) {
        return FacilityResponse.builder()
                .id(facility.getId())
                .facilityName(facility.getFacilityName())
                .managerFirstName(facility.getManagerFirstName())
                .managerLastName(facility.getManagerLastName())
                .assistentFirstName(facility.getAssistentFirstName())
                .assistentLastName(facility.getAssistentLastName())
                .facilityTelephoneNumber(facility.getFacilityTelephoneNumber())
                .location(facility.getLocation())
                .build();
    }

    public Facility toFacility(
            FacilityRequest request
    ) {
        return Facility.builder()
                .facilityName(request.getFacilityName())
                .facilityTelephoneNumber(request.getFacilityTelephoneNumber())
                .managerFirstName(request.getManagerFirstName())
                .managerLastName(request.getManagerLastName())
                .assistentFirstName(request.getAssistentFirstName())
                .assistentLastName(request.getAssistentLastName())
                .location(request.getLocation())
                .build();
    }
}
