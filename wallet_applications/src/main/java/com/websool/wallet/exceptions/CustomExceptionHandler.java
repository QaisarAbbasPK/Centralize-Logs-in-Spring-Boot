package com.websool.wallet.exceptions;

import com.websool.wallet.domain.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleInvalidArgument(MethodArgumentNotValidException exception) {
//        Map<String,String>errorMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(error-> {
//            errorMap.put(error.getField(),error.getDefaultMessage());
//        });
        return new ApiResponse(
                "400",
                exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse handleUserNotFoundException(UsernameNotFoundException exception) {
        return new ApiResponse("400", exception.getMessage(), null);

    }



    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResponse ExpiredJwtException(ExpiredJwtException exception) {
        return new ApiResponse("400", exception.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(GlobalException.class)
    public ApiResponse handleGlobalException(GlobalException exception) {
        return new ApiResponse("400", exception.getMessage(), null);
    }
}
