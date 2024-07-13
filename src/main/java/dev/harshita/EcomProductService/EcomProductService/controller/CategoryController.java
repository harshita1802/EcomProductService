package dev.harshita.EcomProductService.EcomProductService.controller;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.CategoryRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CategoryResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.repository.CategoryRepository;
import dev.harshita.EcomProductService.EcomProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);

        return ResponseEntity.ok(categoryResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryResponseDtoList);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Category>> getAllCategories(){
//        List<Category> category = categoryRepository.findAll();
//
//        return ResponseEntity.ok(category);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable UUID id){
        CategoryResponseDto categoryResponseDto = categoryService.getById(id);

        return ResponseEntity.ok(categoryResponseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateProduct(@PathVariable("id") UUID categoryId, @RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId,categoryRequestDto);

        return ResponseEntity.ok(categoryResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable UUID id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

}