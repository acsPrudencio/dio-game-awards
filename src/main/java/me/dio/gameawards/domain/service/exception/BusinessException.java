package me.dio.gameawards.domain.service.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }
    
}
