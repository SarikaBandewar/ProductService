package com.sk.productservice.practice.models.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_ta")
public class TA extends User{
    Long sessionId;
    Double ratingPerSession;
}
