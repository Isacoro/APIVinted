package com.isabel.api_vinted.controller;

import com.isabel.api_vinted.domain.Producto;
import com.isabel.api_vinted.domain.ProductoDTO;
import com.isabel.api_vinted.exception.ProductoNotFoundException;
import com.isabel.api_vinted.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ProductoController {

    @Autowired
    public ProductoService productoService;

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);


    /**** LISTADO DE TODOS LOS PRODUCTOS ****/

    @GetMapping(value = "/productos", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductos(){
        logger.info("Inicio getProductos");
        Set<Producto> productos = productoService.findAll();
        logger.info("Fin getProductos");
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


    /**** LISTADO DE PRODUCTOS SEGÚN CATEGORÍA ****/

    @GetMapping(value = "/productos/categoria", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductosCategoria(@RequestParam(value = "categoria", defaultValue = "") String categoria){
        logger.info("Inicio getProductosCategoria");
        Set<Producto> productos = productoService.findByCategoria(categoria);
        logger.info("Fin getProductoCategoria");
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }



    /**** LISTADO DE LOS 10 PRODUCTOS CON MÁS PUNTUACIÓN ****/

    @GetMapping(value = "/productos/top", produces = "application/json")
    public ResponseEntity <List<Producto>> getProductosTop(){
        logger.info("Inicio getProductosTop");
        List<Producto> productosTop = productoService.findTopPuntos();
        logger.info("Fin getProductosTop");
        return new ResponseEntity<>(productosTop, HttpStatus.OK);
    }



    /**** AÑADIR PRODUCTOS ****/

    @PostMapping(value = "/productos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Producto> addProducto(@RequestBody ProductoDTO productoDTO){
        logger.info("Inicio addProducto");
        Producto addedProducto = productoService.addProducto(productoDTO);
        logger.info("Fin addProducto");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProducto);
    }



    /**** LISTADO DE PRODUCTOS DE UN USUARIO ****/

    @GetMapping(value = "/productos/{usuario_id}", produces = "application/json")
    public ResponseEntity<Set<Producto>> findByUsuario(@RequestParam(value = "usuario_id") long usuario_id){
        logger.info("Inicio findByUsuario");
        Set<Producto> productosUsuario = productoService.findByUsuario(usuario_id);
        logger.info("Fin findByUsuario");
        return new ResponseEntity<>(productosUsuario, HttpStatus.OK);
    };



    /**** EXCEPCIONES EN CASO DE NO ENCONTRAR EL ELEMENTO ****/

    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(ProductoNotFoundException pnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
