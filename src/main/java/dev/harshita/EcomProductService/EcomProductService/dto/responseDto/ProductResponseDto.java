package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private int id;
    private String title;
    private double price;
    private String category;
    private String description;
}
