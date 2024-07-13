package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.CategoryRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CategoryResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.CategoryNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.exception.CategoryPresentException;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) throws CategoryPresentException{
        if(categoryRepository.findByName(categoryRequestDto.getName()) != null){
            throw new CategoryPresentException("Category with the name is already present!");
        }
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setProducts(new ArrayList<>());

        return EntityToDtoMapper.convertCategoryToResponseDto(categoryRepository.save(category));
    }

    @Override
    public boolean deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for(Category category : categories){
            CategoryResponseDto categoryResponseDto = EntityToDtoMapper.convertCategoryToResponseDto(category);
            categoryResponseDtoList.add(categoryResponseDto);
        }

        return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDto getById(UUID id) {
        Category category = categoryRepository.getReferenceById(id);
        if(category == null){
            throw new CategoryNotFoundException("Category with the id not found!");
        }

        return EntityToDtoMapper.convertCategoryToResponseDto(category);
    }

    @Override
    public Category getByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if(category == null){
            throw new CategoryNotFoundException("Category with the name not found!");
        }

        return category;
    }

    @Override
    public Category addProductToCategory(UUID id, Product savedProduct) {
        Category category = categoryRepository.getReferenceById(id);
        category.getProducts().add(savedProduct);

        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDto updateCategory(UUID categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.getReferenceById(categoryId);

        if(category == null){
            throw new CategoryNotFoundException("Category with the id not found!");
        }

        if(categoryRequestDto.getName() != null){
            category.setName(categoryRequestDto.getName());
        }

        return EntityToDtoMapper.convertCategoryToResponseDto(categoryRepository.save(category));
    }
}