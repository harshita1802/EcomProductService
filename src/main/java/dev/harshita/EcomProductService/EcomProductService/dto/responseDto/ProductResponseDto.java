package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable {
    private double price;
    private String description;
    private String categoryName;
    private RatingResponseDto rating;
}
