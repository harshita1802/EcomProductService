package dev.harshita.EcomProductService.EcomProductService.service;

import dev.harshita.EcomProductService.EcomProductService.client.FakeStoreClient;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.exception.NoProductFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Product> products = fakeStoreClient.getAllProducts();
        if(products == null){
            throw new NoProductFoundException("There are no products to display");
        }
        return products;
    }

    @Override
    public Product getById(int prodId) {
        return null;
    }
}
