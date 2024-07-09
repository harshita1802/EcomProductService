package dev.harshita.EcomProductService.EcomProductService.controller;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.mapper.EntityToDtoMapper;
import dev.harshita.EcomProductService.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponse = new ArrayList<>();
        for(Product product : products){
            productResponse.add(EntityToDtoMapper.convertProductToDto(product));
        }

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable int id){

        Product product = productService.getById(id);

        return ResponseEntity.ok(EntityToDtoMapper.convertProductToDto(product));
    }

}
