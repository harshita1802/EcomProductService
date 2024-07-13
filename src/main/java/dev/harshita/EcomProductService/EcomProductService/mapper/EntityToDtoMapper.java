package dev.harshita.EcomProductService.EcomProductService.mapper;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.CategoryResponseDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ProductResponseDto;
import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.RatingResponseDto;
import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import dev.harshita.EcomProductService.EcomProductService.entity.Product;
import dev.harshita.EcomProductService.EcomProductService.entity.Rating;

public class EntityToDtoMapper {

    public static ProductResponseDto convertProductToResponseDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCategoryName(product.getCategory().getName());

        if(product.getRating() != null){
            productResponseDto.setRating(convertRatingToResponseDto(product.getRating()));
        }
        return productResponseDto;
    }

    private static RatingResponseDto convertRatingToResponseDto(Rating rating) {
        RatingResponseDto ratingResponseDto = new RatingResponseDto();
        ratingResponseDto.setRating(rating.getRating());
        ratingResponseDto.setCount(rating.getCount());

        return ratingResponseDto;
    }

    public static CategoryResponseDto convertCategoryToResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(category.getId());
        categoryResponseDto.setCategoryName(category.getName());
        categoryResponseDto.setProducts(category.getProducts());

        return categoryResponseDto;
    }
}
