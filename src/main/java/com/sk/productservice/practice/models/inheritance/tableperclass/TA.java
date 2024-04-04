package com.sk.productservice.practice.models.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tc_ta")
public class TA extends User {
    Long sessionId;
    Double ratingPerSession;
}
