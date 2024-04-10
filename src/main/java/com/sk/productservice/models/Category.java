package com.sk.productservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;
    private String title;
    private String description;
}
