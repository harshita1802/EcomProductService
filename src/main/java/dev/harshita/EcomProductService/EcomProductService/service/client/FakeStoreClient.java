package dev.harshita.EcomProductService.EcomProductService.service.client;

import dev.harshita.EcomProductService.EcomProductService.dto.requestDto.ProductRequestDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.harshita.EcomProductService.EcomProductService.service.ProductService;

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
    private RedisTemplate<Object,Object> redisTemplate;

    public List<FakeStoreProductResponseDto> getAllProducts(){

        String fakeStoreProductUrl = domainName.concat(productEndpoint);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProductResponseList = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto[].class);

        return List.of(fakeStoreProductResponseList.getBody());
    }


    @Override
    public FakeStoreProductResponseDto getById(int prodId) {

        if(redisTemplate.hasKey(prodId)){
            return (FakeStoreProductResponseDto) redisTemplate.opsForValue().get(prodId);
        }

        String fakeStoreProductUrl = domainName.concat(productEndpoint).concat("/"+prodId);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDto = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto.class);

        redisTemplate.opsForValue().set(prodId, fakeStoreProductResponseDto.getBody());

        return fakeStoreProductResponseDto.getBody();
    }


    @Override
    public FakeStoreProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public boolean deleteProduct(UUID prodId) {
        return false;
    }

    @Override
    public FakeStoreProductResponseDto updateProduct(UUID prodId, ProductRequestDto productRequestDto) {
        return null;
    }


    @Override
    public FakeStoreProductResponseDto getById(UUID prodId) {
        return null;
    }


}
