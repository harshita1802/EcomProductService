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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("customProductService")
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<? extends ProductResponseDto>> getAllProducts(){
        List<? extends ProductResponseDto> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ProductResponseDto> getProductById(@PathVariable UUID id){
        ProductResponseDto productResponseDto = productService.getById(id);

        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping
    public ResponseEntity<? extends ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends ProductResponseDto> updateProduct(@PathVariable("id") UUID prodId, @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.updateProduct(prodId,productRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable UUID id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
