package dev.harshita.EcomProductService.EcomProductService.mapper;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.model.Category;
import dev.harshita.EcomProductService.EcomProductService.model.Product;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityMapper {

    public Product convertProductRequestDtoToEntity(ProductRequestDto productRequestDto, Category category) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setBrand(productRequestDto.getBrand());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(category);

        return product;
    }

}
