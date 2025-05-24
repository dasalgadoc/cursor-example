package com.dsalgado.schools.infrastructure.persistence;

import com.dsalgado.schools.domain.model.School;

public class SchoolMapper {
    private SchoolMapper() {
        // Prevent instantiation
    }

    public static SchoolEntity toEntity(School school) {
        SchoolEntity entity = new SchoolEntity();
        entity.setId(school.getId());
        entity.setName(school.getName());
        entity.setAddress(school.getAddress());
        entity.setPhone(school.getPhone());
        entity.setEmail(school.getEmail());
        entity.setActive(school.isActive());
        return entity;
    }

    public static School toDomain(SchoolEntity entity) {
        School school = new School(
            entity.getName(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail()
        );
        school.setId(entity.getId());
        school.setActive(entity.isActive());
        return school;
    }
} 