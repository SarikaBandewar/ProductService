package com.sk.productservice.practice.models.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_student")
public class Student extends User{
    String batchId;
}
