package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private UUID categoryId;
    private String categoryName;
    private List<Product> products;
}
