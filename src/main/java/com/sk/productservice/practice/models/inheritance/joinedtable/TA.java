package com.sk.productservice.practice.models.inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_ta")
public class TA extends User {
    Long sessionId;
    Double ratingPerSession;
}
