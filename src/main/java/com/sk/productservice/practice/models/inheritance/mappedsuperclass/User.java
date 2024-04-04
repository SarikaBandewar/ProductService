package com.sk.productservice.practice.models.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    Long id;
    String name;
    String emailId;
    String password;
}
