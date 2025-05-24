package com.dsalgado.schools.domain.port;

import com.dsalgado.schools.domain.model.School;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchoolRepository {
    School save(School school);
    Optional<School> findById(UUID id);
    List<School> findAll();
    void delete(UUID id);
    boolean existsById(UUID id);
} 