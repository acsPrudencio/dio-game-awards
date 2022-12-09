package me.dio.gameawards.domain.controller;

import me.dio.gameawards.domain.service.exception.BusinessException;
import me.dio.gameawards.domain.service.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ApiErrorDto> handlerNoContent(NoContentException noContentException){
        ApiErrorDto apiErrorDto = new ApiErrorDto(noContentException.getMessage());
        return ResponseEntity.badRequest().body(apiErrorDto);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDto> handlerBusinessException(BusinessException businessException){
        ApiErrorDto apiErrorDto = new ApiErrorDto(businessException.getMessage());
        return ResponseEntity.badRequest().body(apiErrorDto);
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDto> handlerBusinessException(Throwable throwable){
        throwable.printStackTrace();
        ApiErrorDto apiErrorDto = new ApiErrorDto(throwable.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorDto);
    }
}
