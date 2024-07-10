package dev.harshita.EcomProductService.EcomProductService.mapper;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;

public class DtoToEntityMapper {

    public static Product convertFakeProductDtoToEntity(FakeStoreProductResponseDto fakeStoreProductResponse){
        Product product = new Product();
        product.setId(fakeStoreProductResponse.getId());
        product.setTitle(fakeStoreProductResponse.getTitle());
        product.setCategory(fakeStoreProductResponse.getCategory());
        product.setDescription(fakeStoreProductResponse.getDescription());
        product.setPrice(fakeStoreProductResponse.getPrice());
        return product;
    }

    public static Product convertProductRequestDtoToEntity(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());

        return product;
    }
}
