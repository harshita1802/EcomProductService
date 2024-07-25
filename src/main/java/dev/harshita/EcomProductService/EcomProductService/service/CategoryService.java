package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.CategoryRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CategoryResponseDto;
import dev.harshita.EcomProductService.EcomProductService.model.Category;
import dev.harshita.EcomProductService.EcomProductService.model.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDto addCategory(CategoryRequestDto category);
    CategoryResponseDto updateCategory(UUID categoryId, CategoryRequestDto categoryRequestDto);
    boolean deleteCategory(UUID id);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getById(UUID id);
    Category getByName(String categoryName);
    Category addProductToCategory(UUID id, Product savedProduct);
}
