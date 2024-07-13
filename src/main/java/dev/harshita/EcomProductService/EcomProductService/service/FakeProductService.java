package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.client.FakeStoreClient;
import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.NoProductsFoundException;
import dev.harshita.EcomProductService.EcomProductService.exception.ProductNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("fakeProductService")
public class FakeProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public ProductResponseDto addProduct(ProductRequestDto product) {
        return null;
    }

    public boolean deleteProduct(UUID prodId) {
        return false;
    }

    public Product updateProduct(UUID prodId) {
        return null;
    }

    public List<ProductResponseDto> getAllProducts() throws NoProductsFoundException {
        List<FakeStoreProductResponseDto> responseDto = fakeStoreClient.getAllProducts();

        if(responseDto == null){
            throw new NoProductsFoundException("There are no products to display!");
        }

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductResponseDto fakeStoreProductResponse : responseDto){
            products.add(DtoToEntityMapper.convertFakeProductDtoToEntity(fakeStoreProductResponse));
        }

        List<ProductResponseDto> productResponseDto = new ArrayList<>();

        for(Product product : products){
            productResponseDto.add(entityToDtoMapper.convertProductToResponseDto(product));
        }

        return productResponseDto;
    }

    public ProductResponseDto getById(int prodId) throws ProductNotFoundException {

        FakeStoreProductResponseDto responseDto = fakeStoreClient.getProductById(prodId);

        if(responseDto == null){
            throw new ProductNotFoundException("Product not found!");
        }

        Product product = DtoToEntityMapper.convertFakeProductDtoToEntity(responseDto);

        return entityToDtoMapper.convertProductToResponseDto(product);
    }

}
