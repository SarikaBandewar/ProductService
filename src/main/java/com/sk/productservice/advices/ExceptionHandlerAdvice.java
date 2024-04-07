package com.sk.productservice.advices;

import com.sk.productservice.dto.ArithmeticExceptionDto;
import com.sk.productservice.dto.ArrayIndexOutOfBoundsExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> arithmeticExceptionHandler(){
        ArithmeticExceptionDto arithmeticExceptionDto = new ArithmeticExceptionDto();
        arithmeticExceptionDto.setMessage("Arithmetic Exception");
        arithmeticExceptionDto.setDetail("Something went wrong");
        return new ResponseEntity<>(arithmeticExceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundsExceptionDto> arrayIndexOutOfBoundsExceptionHandler(){
        ArrayIndexOutOfBoundsExceptionDto arrayIndexOutOfBoundsExceptionDto = new ArrayIndexOutOfBoundsExceptionDto();
        arrayIndexOutOfBoundsExceptionDto.setMessage("ArrayIndexOutOfBoundsException");
        arrayIndexOutOfBoundsExceptionDto.setDetail("Something went wrong");
        return new ResponseEntity<>(arrayIndexOutOfBoundsExceptionDto, HttpStatus.BAD_REQUEST);
    }
}
