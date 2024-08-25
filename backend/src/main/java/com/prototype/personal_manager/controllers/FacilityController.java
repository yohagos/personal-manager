package com.prototype.personal_manager.controllers;

import com.prototype.personal_manager.common.PageResponse;
import com.prototype.personal_manager.requests.FacilityRequest;
import com.prototype.personal_manager.responses.FacilityResponse;
import com.prototype.personal_manager.services.FacilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
@Tag(name = "Facilities")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<PageResponse<FacilityResponse>> getFacilities(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(facilityService.loadFacilities(page, size));
    }

    @GetMapping("/{facilityId}")
    public ResponseEntity<FacilityResponse> getFacilityById(
            @PathVariable(name = "facilityId") UUID facilityId
    ) {
        return ResponseEntity.ok(facilityService.loadFacilityById(facilityId));
    }

    @PostMapping("/add")
    public ResponseEntity<FacilityResponse> addFacility(
            @RequestBody FacilityRequest request
    ) {
        return ResponseEntity.ok(facilityService.saveFacility(request));
    }

    @PutMapping("/update/{facilityId}")
    public ResponseEntity<FacilityResponse> updateFacility(
            @PathVariable(name = "facilityId") UUID facilityId,
            @RequestBody FacilityRequest request
    ) {
        return ResponseEntity.ok(facilityService.updateFacility(facilityId, request));
    }

}
