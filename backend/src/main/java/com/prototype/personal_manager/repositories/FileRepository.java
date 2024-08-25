package com.prototype.personal_manager.repositories;

import com.prototype.personal_manager.entities.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<Files, UUID> {
}
