package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.NoProductsFoundException;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private EntityToDtoMapper entityToDtoMapper;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Category savedCategory = categoryService.getByName(productRequestDto.getCategoryName());

        Product product = DtoToEntityMapper.convertProductRequestDtoToEntity(productRequestDto, savedCategory);

        Product savedProduct = productRepository.save(product);

        categoryService.addProductToCategory(savedCategory.getId(),savedProduct);

        ProductResponseDto productResponseDto = entityToDtoMapper.convertProductToResponseDto(savedProduct);

        return productResponseDto;
    }


    @Override
    public ProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto) throws EntityNotFoundException {
        Product savedProduct = productRepository.getReferenceById(prodId);

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

        return entityToDtoMapper.convertProductToResponseDto(productRepository.save(savedProduct));

    }

    @Override
    public boolean deleteProduct(UUID prodId) {
        productRepository.deleteById(prodId);
        return true;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() throws NoProductsFoundException {
        List<Product> products = productRepository.findAll();

        if(products == null || products.isEmpty()){
            throw new NoProductsFoundException("Products not found!");
        }

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = entityToDtoMapper.convertProductToResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }

       return productResponseDtoList;
    }


    @Override
    public ProductResponseDto getById(UUID prodId) throws EntityNotFoundException{
        Product product = productRepository.getReferenceById(prodId);

        return entityToDtoMapper.convertProductToResponseDto(product);
    }
}
