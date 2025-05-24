package com.dsalgado.schools.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "schools")
@Getter
@Setter
public class SchoolEntity {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean active;
} 