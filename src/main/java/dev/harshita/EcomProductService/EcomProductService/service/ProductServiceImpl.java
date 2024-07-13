package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.ProductNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Category savedCategory = categoryService.getByName(productRequestDto.getCategoryName());

        Product product = DtoToEntityMapper.convertProductRequestDtoToEntity(productRequestDto, savedCategory);

        Product savedProduct = productRepository.save(product);

        categoryService.addProductToCategory(savedCategory.getId(),savedProduct);

        ProductResponseDto productResponseDto = EntityToDtoMapper.convertProductToResponseDto(savedProduct);

        return productResponseDto;
    }


    @Override
    public ProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto) {
        Product savedProduct = productRepository.getReferenceById(prodId);

        if(savedProduct == null){
            throw new ProductNotFoundException("Product with the id not found!");
        }

        if(productRequestDto.getName() != null){
            savedProduct.setName(productRequestDto.getName());
        }
        if(productRequestDto.getBrand() != null){
            savedProduct.setBrand(productRequestDto.getBrand());
        }
        if(productRequestDto.getPrice() != 0){
            savedProduct.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getDescription() != null){
            savedProduct.setDescription(productRequestDto.getDescription());
        }

        Category category = categoryService.getByName(productRequestDto.getCategoryName());
        savedProduct.setCategory(category);

        return EntityToDtoMapper.convertProductToResponseDto(productRepository.save(savedProduct));

    }


    @Override
    public boolean deleteProduct(UUID prodId) {
        productRepository.deleteById(prodId);
        return true;
    }


    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = EntityToDtoMapper.convertProductToResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }

       return productResponseDtoList;
    }


    @Override
    public ProductResponseDto getById(UUID prodId) {
        Product product = productRepository.getReferenceById(prodId);

        if(product == null){
            throw new ProductNotFoundException("Product with the id not found!");
        }

        return EntityToDtoMapper.convertProductToResponseDto(product);
    }
}
