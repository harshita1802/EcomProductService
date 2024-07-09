package dev.harshita.EcomProductService.EcomProductService.client;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.FakeStoreProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.mapper.DtoToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreClientImpl implements FakeStoreClient{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.base.url}")
    private String domainName;

    @Value("${fakestore.api.product.endpoint}")
    private String productEndpoint;


    public List<FakeStoreProductResponseDto> getAllProducts(){

        String fakeStoreProductUrl = domainName.concat(productEndpoint);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProductResponseList = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto[].class);

        return List.of(fakeStoreProductResponseList.getBody());
    }

    public FakeStoreProductResponseDto getProductById(int prodId){

        String fakeStoreProductUrl = domainName.concat(productEndpoint).concat("/"+prodId);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponse = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductResponseDto.class);

        return fakeStoreProductResponse.getBody();
    }
}
