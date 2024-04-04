package com.sk.productservice.practice.models.inheritance.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Instructor extends User {
    String batchId;
    String subject;
}
