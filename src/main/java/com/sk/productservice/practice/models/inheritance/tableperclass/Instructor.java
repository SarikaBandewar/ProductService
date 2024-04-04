package com.sk.productservice.practice.models.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tc_instructor")
public class Instructor extends User {
    String batchId;
    String subject;
}
