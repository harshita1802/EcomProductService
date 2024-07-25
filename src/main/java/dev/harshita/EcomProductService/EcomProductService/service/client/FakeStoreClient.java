package dev.harshita.EcomProductService.EcomProductService.service.client;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.harshita.EcomProductService.EcomProductService.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("fakeStoreService")
public class FakeStoreClient implements ProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.base.url}")
    private String domainName;

    @Value("${fakestore.api.product.endpoint}")
    private String productEndpoint;

    @Autowired
    private RedisTemplate<Integer,Object> redisTemplate;

    public List<ProductResponseDto> getAllProducts(){

        String fakeStoreProductUrl = domainName.concat(productEndpoint);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProductResponseList = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto[].class);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(FakeStoreProductResponseDto fakeStoreProductResponseDto : List.of(fakeStoreProductResponseList.getBody())){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(fakeStoreProductResponseDto.getId());
            productResponseDto.setName(fakeStoreProductResponseDto.getTitle());
            productResponseDto.setPrice(fakeStoreProductResponseDto.getPrice());
            productResponseDto.setCategoryName(fakeStoreProductResponseDto.getCategory());
            productResponseDto.setDescription(fakeStoreProductResponseDto.getDescription());
            productResponseDto.setRating(fakeStoreProductResponseDto.getRating());

            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }


    @Override
    public ProductResponseDto getById(int prodId) {

        if(redisTemplate.hasKey(prodId)){
            return (ProductResponseDto) redisTemplate.opsForValue().get(prodId);
        }

        String fakeStoreProductUrl = domainName.concat(productEndpoint).concat("/"+prodId);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDto = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto.class);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(fakeStoreProductResponseDto.getBody().getId());
        productResponseDto.setName(fakeStoreProductResponseDto.getBody().getTitle());
        productResponseDto.setPrice(fakeStoreProductResponseDto.getBody().getPrice());
        productResponseDto.setCategoryName(fakeStoreProductResponseDto.getBody().getCategory());
        productResponseDto.setDescription(fakeStoreProductResponseDto.getBody().getDescription());
        productResponseDto.setRating(fakeStoreProductResponseDto.getBody().getRating());

        redisTemplate.opsForValue().set(prodId, productResponseDto);

        return productResponseDto;
    }


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public boolean deleteProduct(UUID prodId) {
        return false;
    }

    @Override
    public ProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto) {
        return null;
    }


    @Override
    public ProductResponseDto getByProductId(UUID prodId) {
        return null;
    }


}
