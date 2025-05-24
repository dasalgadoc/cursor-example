package com.dsalgado.schools.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class School {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean active;

    public School(String name, String address, String phone, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
} 