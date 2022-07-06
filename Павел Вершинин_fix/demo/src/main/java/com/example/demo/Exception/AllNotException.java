package com.example.demo.Exception;

public class AllNotException extends RuntimeException{
    public AllNotException(Long id){
        super("Could not find auto "+ id);
    }
}
