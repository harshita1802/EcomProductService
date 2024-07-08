package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    boolean deleteProduct(int prodId);
    Product updateProduct(int prodId);
    List<Product> getAllProducts();
    Product getById(int prodId);
}
