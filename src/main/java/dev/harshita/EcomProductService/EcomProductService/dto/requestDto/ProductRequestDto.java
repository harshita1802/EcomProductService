package dev.harshita.EcomProductService.EcomProductService.dto.requestDto;

import dev.harshita.EcomProductService.EcomProductService.entity.constant.RegisteredBrand;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductRequestDto {
    private String name;
    private RegisteredBrand brand;
    private double price;
    private String description;
    private String categoryName;
}
