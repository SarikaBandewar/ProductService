package com.sk.productservice.practice.models.inheritance.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("3")
public class TA extends User {
    Long sessionId;
    Double ratingPerSession;
}
