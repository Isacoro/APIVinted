package com.isabel.api_vinted.exception;

public class UsuarioNotFoundException extends  RuntimeException {

    public UsuarioNotFoundException(){
        super();
    }

    public UsuarioNotFoundException(String message){
        super(message);
    }

    public UsuarioNotFoundException(long id){
        super("Usuario Not Found " + id);
    }
}
