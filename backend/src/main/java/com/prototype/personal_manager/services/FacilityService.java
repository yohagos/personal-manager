package com.prototype.personal_manager.services;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.entities.Facility;
import com.prototype.personal_manager.mappers.FacilityMapper;
import com.prototype.personal_manager.repositories.FacilityRepository;
import com.prototype.personal_manager.requests.FacilityRequest;
import com.prototype.personal_manager.responses.FacilityResponse;
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
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Transactional
    public PageResponse<FacilityResponse> loadFacilities(
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Facility> facilities = facilityRepository.findAllDisplayableFacilities(pageable);
        List<FacilityResponse> facilityResponses = facilities.stream()
                .map(facilityMapper::toFacilityResponse)
                .toList();
        return new PageResponse<>(
                facilityResponses,
                facilities.getNumber(),
                facilities.getSize(),
                facilities.getTotalElements(),
                facilities.getTotalPages(),
                facilities.isFirst(),
                facilities.isLast()
        );
    }

    @Transactional
    public FacilityResponse loadFacilityById(UUID facilityId) {
        var facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Facility with Id "+facilityId));
        return facilityMapper.toFacilityResponse(facility);
    }

    public FacilityResponse saveFacility(FacilityRequest request) {
        var facility = facilityMapper.toFacility(request);
        return facilityMapper.toFacilityResponse(facilityRepository.save(facility));
    }

    public FacilityResponse updateFacility(
            UUID facilityId,
            FacilityRequest request
    ) {
        var fac = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Facility by Id " + facilityId));

        Optional.of(request.getFacilityName())
                .filter(name -> !name.isEmpty() || !name.equals(fac.getFacilityName()))
                .ifPresent(fac::setFacilityName);
        Optional.of(request.getManagerFirstName())
                .filter(name -> !name.isEmpty() || !name.equals(fac.getManagerFirstName()))
                .ifPresent(fac::setManagerFirstName);
        Optional.of(request.getManagerLastName())
                .filter(name -> !name.isEmpty() || !name.equals(fac.getManagerLastName()))
                .ifPresent(fac::setManagerLastName);
        Optional.of(request.getAssistentFirstName())
                .filter(name -> !name.isEmpty() || !name.equals(fac.getAssistentFirstName()))
                .ifPresent(fac::setAssistentFirstName);
        Optional.of(request.getAssistentLastName())
                .filter(name -> !name.isEmpty() || !name.equals(fac.getAssistentLastName()))
                .ifPresent(fac::setAssistentLastName);
        Optional.of(request.getFacilityTelephoneNumber())
                .filter(number -> !number.isEmpty() || !number.equals(fac.getFacilityTelephoneNumber()))
                .ifPresent(fac::setFacilityTelephoneNumber);
        Optional.of(request.getLocation())
                .filter(loc -> !loc.equals(fac.getLocation()))
                .ifPresent(fac::setLocation);
        return facilityMapper.toFacilityResponse(facilityRepository.save(fac));
    }
}
