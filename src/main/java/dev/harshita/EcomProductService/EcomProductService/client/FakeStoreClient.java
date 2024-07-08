package dev.harshita.EcomProductService.EcomProductService.client;

import dev.harshita.EcomProductService.EcomProductService.entity.Product;

import java.util.List;

public interface FakeStoreClient {
    List<Product> getAllProducts();
}
