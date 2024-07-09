package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.client.FakeStoreClient;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.NoProductFoundException;
import dev.harshita.EcomProductService.EcomProductService.exception.ProductNotFoundException;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductService implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(int prodId) {
        return false;
    }

    @Override
    public Product updateProduct(int prodId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws NoProductFoundException{
        List<FakeStoreProductResponseDto> responseDto = fakeStoreClient.getAllProducts();

        if(responseDto == null){
            throw new NoProductFoundException("There are no products to display!");
        }

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductResponseDto fakeStoreProductResponse : responseDto){
            products.add(DtoToEntityMapper.convertFakeProductDtoToEntity(fakeStoreProductResponse));
        }

        return products;
    }

    @Override
    public Product getById(int prodId) throws ProductNotFoundException {

        FakeStoreProductResponseDto responseDto = fakeStoreClient.getProductById(prodId);

        if(responseDto == null){
            throw new ProductNotFoundException("Product not found!");
        }

        return DtoToEntityMapper.convertFakeProductDtoToEntity(responseDto);
    }
}
