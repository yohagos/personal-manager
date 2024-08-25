package com.prototype.personal_manager.repositories;

import com.prototype.personal_manager.entities.Facility;
import com.prototype.personal_manager.entities.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("""
            SELECT loc
            FROM Location loc
            """)
    Page<Facility> findAllDisplayableLocations(Pageable pageable);
}
