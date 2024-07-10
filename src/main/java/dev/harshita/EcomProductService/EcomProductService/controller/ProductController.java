package dev.harshita.EcomProductService.EcomProductService.controller;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<ProductResponseDto> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable UUID id){

        ProductResponseDto productResponseDto = productService.getById(id);

        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }

}
