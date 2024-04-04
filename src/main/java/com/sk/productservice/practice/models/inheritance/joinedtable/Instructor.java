package com.sk.productservice.practice.models.inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_instructor")
public class Instructor extends User {
    String batchId;
    String subject;
}
