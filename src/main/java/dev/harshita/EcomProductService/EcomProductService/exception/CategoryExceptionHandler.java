package dev.harshita.EcomProductService.EcomProductService.exception;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ExceptionResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotPresent(EntityNotFoundException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                e.getMessage(),
                404
        );

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity handleCategoryNotPresent(CategoryNotFoundException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                e.getMessage(),
                404
        );

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryPresentException.class)
    public ResponseEntity handleCategoryPresent(CategoryPresentException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                e.getMessage(),
                409
        );

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.CONFLICT);
    }
}
