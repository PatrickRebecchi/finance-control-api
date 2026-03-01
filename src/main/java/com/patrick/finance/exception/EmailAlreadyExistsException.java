package com.patrick.finance.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String mensagem){
        super(mensagem);
    }
}
