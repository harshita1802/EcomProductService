package dev.harshita.EcomProductService.EcomProductService.mapper;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;

public class EntityToDtoMapper {

    public static ProductResponseDto convertProductToDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setTitle(product.getTitle());

        return productResponseDto;
    }
}
