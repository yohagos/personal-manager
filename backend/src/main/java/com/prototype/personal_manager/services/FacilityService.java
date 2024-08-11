package com.prototype.personal_manager.services;

import com.prototype.personal_manager.repositories.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public void getTest() {

    }
}
