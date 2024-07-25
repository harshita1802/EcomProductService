package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import dev.harshita.EcomProductService.EcomProductService.model.constant.RegisteredBrand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
//    private UUID id;      //customProduct id
    private int id;         //fakestoreProduct id
    private String name;
    private RegisteredBrand brand;
    private double price;
    private String description;
    private String categoryName;
    private RatingResponseDto rating;
}
