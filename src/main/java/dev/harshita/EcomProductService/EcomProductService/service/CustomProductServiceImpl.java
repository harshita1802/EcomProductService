package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CustomProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.model.Category;
import dev.harshita.EcomProductService.EcomProductService.model.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.CategoryNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.exception.NoProductsFoundException;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("customProductService")
public class CustomProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EntityToDtoMapper entityToDtoMapper;
    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Override
    public CustomProductResponseDto addProduct(ProductRequestDto productRequestDto) throws CategoryNotFoundException {
        Category savedCategory = categoryService.getByName(productRequestDto.getCategoryName());

        Product product = dtoToEntityMapper.convertProductRequestDtoToEntity(productRequestDto, savedCategory);

        Product savedProduct = productRepository.save(product);

        categoryService.addProductToCategory(savedCategory.getId(),savedProduct);

        CustomProductResponseDto customProductResponseDto = entityToDtoMapper.convertProductToResponseDto(savedProduct);

        return customProductResponseDto;
    }


    @Override
    public CustomProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto) throws EntityNotFoundException {
        Product savedProduct = productRepository.getReferenceById(prodId);

        if(productRequestDto.getPrice() != 0){
            savedProduct.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getDescription() != null){
            savedProduct.setDescription(productRequestDto.getDescription());
        }

        return entityToDtoMapper.convertProductToResponseDto(productRepository.save(savedProduct));
    }

    @Override
    public boolean deleteProduct(UUID prodId) {
        productRepository.deleteById(prodId);

        return true;
    }

    @Override
    public List<CustomProductResponseDto> getAllProducts() throws NoProductsFoundException {
        List<Product> products = productRepository.findAll();

        if(products == null || products.isEmpty()){
            throw new NoProductsFoundException("Products not found!");
        }

        List<CustomProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products){
            CustomProductResponseDto productResponseDto = entityToDtoMapper.convertProductToResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }

       return productResponseDtoList;
    }


    @Override
    public CustomProductResponseDto getById(UUID prodId) throws EntityNotFoundException{
        if(redisTemplate.hasKey(prodId)){
            return (CustomProductResponseDto) redisTemplate.opsForValue().get(prodId);
        }

        Product product = productRepository.getReferenceById(prodId);

        CustomProductResponseDto customProductResponseDto = entityToDtoMapper.convertProductToResponseDto(product);

        redisTemplate.opsForValue().set(prodId,customProductResponseDto);

        return customProductResponseDto;
    }

    @Override
    public ProductResponseDto getById(int prodId) {
        return null;
    }

}
