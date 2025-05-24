package com.dsalgado.schools.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataSchoolRepository extends JpaRepository<SchoolEntity, UUID> {
} 