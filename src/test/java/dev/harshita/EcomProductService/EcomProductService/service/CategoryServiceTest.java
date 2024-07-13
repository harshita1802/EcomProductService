package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.CategoryRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CategoryResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.CategoryNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.exception.CategoryPresentException;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EntityToDtoMapper entityToDtoMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCategoryWhenCategoryAlreadyPresent(){

        //arrange
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Mobile and Accessories");

        Category category = new Category();
        category.setName("Mobile and Accessories");

        when(categoryRepository.findByName(categoryRequestDto.getName())).thenReturn(category);

        //act and assert
        Assertions.assertThrows(CategoryPresentException.class,() -> categoryService.addCategory(categoryRequestDto));

        verify(categoryRepository).findByName(categoryRequestDto.getName());
    }

    @Test
    public void testAddCategorySuccessfully(){

        //arrange
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Mobile and Accessories");

        UUID randomID = UUID.randomUUID();

        Category savedCategory = new Category();
        savedCategory.setName(categoryRequestDto.getName());
        savedCategory.setProducts(new ArrayList<>());
        savedCategory.setId(randomID);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryName("Mobile and Accessories");
        categoryResponseDto.setProducts(new ArrayList<>());
        categoryResponseDto.setCategoryId(randomID);

        when(categoryRepository.findByName(categoryRequestDto.getName())).thenReturn(null);

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);

        when(categoryRepository.save(categoryCaptor.capture())).thenReturn(savedCategory);
        when(entityToDtoMapper.convertCategoryToResponseDto(savedCategory)).thenReturn(categoryResponseDto);

        //act
        CategoryResponseDto result = categoryService.addCategory(categoryRequestDto);

        //assert
        verify(categoryRepository).findByName(categoryRequestDto.getName());
        verify(categoryRepository).save(categoryCaptor.capture());
        verify(entityToDtoMapper).convertCategoryToResponseDto(savedCategory);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryResponseDto,result);

    }

    @Test
    public void testUpdateCategorySuccessfully(){
        //arrange
        UUID randomId = UUID.randomUUID();

        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Mobile and Accessories");

        Category existingCategory = new Category();
        existingCategory.setId(randomId);
        existingCategory.setName("Computer and Accessories");

        Category updatedCategory = new Category();
        updatedCategory.setId(randomId);
        updatedCategory.setName("Mobile and Accessories");

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(randomId);
        categoryResponseDto.setCategoryName("Mobile and Accessories");

        when(categoryRepository.getReferenceById(randomId)).thenReturn(existingCategory);
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);
        when(entityToDtoMapper.convertCategoryToResponseDto(updatedCategory)).thenReturn(categoryResponseDto);

        //act
        CategoryResponseDto result = categoryService.updateCategory(randomId,categoryRequestDto);

        //assert
        verify(categoryRepository).getReferenceById(randomId);
        verify(categoryRepository).save(existingCategory);
        verify(entityToDtoMapper).convertCategoryToResponseDto(updatedCategory);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryRequestDto.getName(),result.getCategoryName());
        Assertions.assertEquals(categoryResponseDto,result);

    }

    @Test
    public void testAddProductToCategorySuccessfully(){
        //arrange
        UUID randomId = UUID.randomUUID();

        Product product = new Product();

        Category existingCategory = new Category();
        existingCategory.setProducts(new ArrayList<>());

        Category updatedCategory = new Category();
        updatedCategory.setProducts(new ArrayList<>());

        when(categoryRepository.getReferenceById(randomId)).thenReturn(existingCategory);
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);

        //act
        Category result = categoryService.addProductToCategory(randomId,product);

        //assert
        verify(categoryRepository).getReferenceById(randomId);
        verify(categoryRepository).save(existingCategory);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedCategory,result);
    }

    @Test
    public void testGetAllProductsSuccessfully(){
        //arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        categoryResponseDtoList.add(categoryResponseDto);

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Mockito.when(entityToDtoMapper.convertCategoryToResponseDto(categories.get(0))).thenReturn(categoryResponseDto);

        //act
        List<CategoryResponseDto> result = categoryService.getAllCategories();

        //assert
        verify(categoryRepository).findAll();
        verify(entityToDtoMapper).convertCategoryToResponseDto(categories.get(0));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryResponseDtoList,result);
    }

    @Test
    public void testGetByIdSuccessfully(){
        //arrange
        UUID randomId = UUID.randomUUID();

        Category category = new Category();

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        Mockito.when(categoryRepository.getReferenceById(randomId)).thenReturn(category);
        Mockito.when(entityToDtoMapper.convertCategoryToResponseDto(category)).thenReturn(categoryResponseDto);

        //act
        CategoryResponseDto result = categoryService.getById(randomId);

        //assert
        verify(categoryRepository).getReferenceById(randomId);
        verify(entityToDtoMapper).convertCategoryToResponseDto(category);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryResponseDto,result);
    }

    @Test
    public void testGetByNameSuccessfully(){

        //arrange
        String categoryName = "Mobile and Accessories";

        Category category = new Category();
        category.setName(categoryName);

        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(category);

        //act
        Category result = categoryService.getByName(categoryName);

        //assert
        verify(categoryRepository).findByName(categoryName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(category,result);

    }

    @Test
    public void testGetByNameWhenCategoryNameIsNotFound(){

        //arrange
        String categoryName = "Mobile and Accessories";

        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(null);

        //act and assert
        Assertions.assertThrows(CategoryNotFoundException.class,() -> categoryService.getByName(categoryName));

    }

    @Test
    public void testDeleteCategorySuccessfully(){

        //arrange
        UUID randomId = UUID.randomUUID();

        //act
        boolean result = categoryService.deleteCategory(randomId);

        //assert
        Assertions.assertEquals(true,result);
    }


}
