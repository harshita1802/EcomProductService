package dev.harshita.EcomProductService.EcomProductService.mapper;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityMapper {

    public Product convertFakeProductDtoToEntity(FakeStoreProductResponseDto fakeStoreProductResponse){
        Product product = new Product();
        product.setId(fakeStoreProductResponse.getId());
        product.setName(fakeStoreProductResponse.getTitle());
//        product.setCategory(fakeStoreProductResponse.getCategory());
        product.setDescription(fakeStoreProductResponse.getDescription());
        product.setPrice(fakeStoreProductResponse.getPrice());
        return product;
    }

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
