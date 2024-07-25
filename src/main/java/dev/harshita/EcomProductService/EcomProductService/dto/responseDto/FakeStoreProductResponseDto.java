package dev.harshita.EcomProductService.EcomProductService.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductResponseDto extends ProductResponseDto implements Serializable {
    private int id;
    private String title;
}
