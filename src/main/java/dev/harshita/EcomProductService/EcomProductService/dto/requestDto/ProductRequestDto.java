package dev.harshita.EcomProductService.EcomProductService.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    private String title;
    private double price;
    private String category;
    private String description;
}
