package com.prototype.personal_manager.controllers;

import com.prototype.personal_manager.services.FacilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
@Tag(name = "Facilities")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<?> getF() {
        facilityService.getTest();
        return ResponseEntity.accepted().build();
    }
}
