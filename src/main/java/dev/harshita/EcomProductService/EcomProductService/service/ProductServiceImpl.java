package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = DtoToEntityMapper.convertProductRequestDtoToEntity(productRequestDto);

        Product savedProduct = productRepository.save(product);

        return EntityToDtoMapper.convertProductToDto(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID prodId) {
        return false;
    }

    @Override
    public Product updateProduct(UUID prodId) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
       return null;
    }

    @Override
    public ProductResponseDto getById(UUID prodId) {
        Product product = productRepository.getReferenceById(prodId);
        return EntityToDtoMapper.convertProductToDto(product);
    }
}
