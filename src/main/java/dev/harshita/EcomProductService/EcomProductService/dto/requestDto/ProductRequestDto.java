package dev.harshita.EcomProductService.EcomProductService.dto.requestDto;

import dev.harshita.EcomProductService.EcomProductService.model.constant.RegisteredBrand;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    private String name;
    private RegisteredBrand brand;
    private double price;
    private String description;
    private String categoryName;
}
