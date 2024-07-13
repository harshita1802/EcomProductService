package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import dev.harshita.EcomProductService.EcomProductService.entity.constant.RegisteredBrand;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private UUID id;
    private String name;
    private RegisteredBrand brand;
    private double price;
    private String description;
    private String categoryName;
    private RatingResponseDto rating;
}
