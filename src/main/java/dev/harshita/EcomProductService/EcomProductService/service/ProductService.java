package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto product);
    boolean deleteProduct(UUID prodId);
    Product updateProduct(UUID prodId);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getById(UUID prodId);
}
