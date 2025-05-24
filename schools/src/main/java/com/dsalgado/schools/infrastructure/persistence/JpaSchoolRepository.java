package com.dsalgado.schools.infrastructure.persistence;

import com.dsalgado.schools.domain.model.School;
import com.dsalgado.schools.domain.port.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaSchoolRepository implements SchoolRepository {
    private final SpringDataSchoolRepository repository;

    @Override
    public School save(School school) {
        SchoolEntity entity = SchoolMapper.toEntity(school);
        return SchoolMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<School> findById(UUID id) {
        return repository.findById(id).map(SchoolMapper::toDomain);
    }

    @Override
    public List<School> findAll() {
        return repository.findAll().stream()
                .map(SchoolMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
} 