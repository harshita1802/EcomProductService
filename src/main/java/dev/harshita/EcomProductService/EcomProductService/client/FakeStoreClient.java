package dev.harshita.EcomProductService.EcomProductService.client;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;

import java.util.List;

public interface FakeStoreClient {
    List<FakeStoreProductResponseDto> getAllProducts();
    FakeStoreProductResponseDto getProductById(int prodId);
}
