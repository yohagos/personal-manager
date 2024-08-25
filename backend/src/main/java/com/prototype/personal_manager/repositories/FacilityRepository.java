package com.prototype.personal_manager.repositories;

import com.prototype.personal_manager.entities.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {
    @Query("""
            SELECT fac
            FROM Facility fac
            """)
    Page<Facility> findAllDisplayableFacilities(Pageable pageable);

    /*@Query("""
            SELECT fac
            FROM Facility fac
            WHERE fac.location.cityName == :cityName
            """)
    Page<Facility> findAllDisplayableFacilitiesByCityName(Pageable pageable, String cityName);*/
}
