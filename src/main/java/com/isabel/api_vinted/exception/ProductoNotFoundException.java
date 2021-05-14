package com.isabel.api_vinted.exception;

public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(){
        super();
    }

    public ProductoNotFoundException(String message){
        super(message);
    }

    public ProductoNotFoundException(long id){
        super("Producto Not Found " + id);
    }
}
