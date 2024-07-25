package dev.harshita.EcomProductService.EcomProductService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.harshita.EcomProductService.EcomProductService.model.constant.RegisteredBrand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;

    @Enumerated(EnumType.STRING)
    private RegisteredBrand brand;

    private double price;

    private String description;

    @ManyToOne
    @JsonBackReference
    private Category category;

    @OneToOne
    private Rating rating;
}
