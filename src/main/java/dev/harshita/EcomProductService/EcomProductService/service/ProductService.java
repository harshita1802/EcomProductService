package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    boolean deleteProduct(UUID prodId);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getById(UUID prodId);
    ProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto);
}
