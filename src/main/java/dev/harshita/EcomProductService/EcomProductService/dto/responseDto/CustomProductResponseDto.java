package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import dev.harshita.EcomProductService.EcomProductService.model.constant.RegisteredBrand;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CustomProductResponseDto extends ProductResponseDto implements Serializable {
    private UUID id;
    private String name;
    private RegisteredBrand brand;
}
