package dev.harshita.EcomProductService.EcomProductService.exception;

import dev.harshita.EcomProductService.EcomProductService.dto.responseDto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler({NoProductFoundException.class,ProductNotFoundException.class})
    public ResponseEntity handleNoProductFound(RuntimeException e){
        ExceptionResponseDto responseDto = new ExceptionResponseDto(
                e.getMessage(),
                404
        );
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
