package com.dsalgado.schools.application.usecase;

import com.dsalgado.schools.domain.model.School;
import com.dsalgado.schools.domain.port.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSchoolUseCase {
    private final SchoolRepository schoolRepository;

    public School execute(String name, String address, String phone, String email) {
        School school = new School(name, address, phone, email);
        return schoolRepository.save(school);
    }
} 