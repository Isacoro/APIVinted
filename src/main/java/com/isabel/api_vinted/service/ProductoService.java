package com.isabel.api_vinted.service;

import com.isabel.api_vinted.domain.Producto;
import com.isabel.api_vinted.domain.ProductoDTO;

import java.util.List;
import java.util.Set;

public interface ProductoService {

    //Consultar todos
    Set<Producto> findAll();

    //Consultar por categoria
    Set<Producto> findByCategoria(String categoria);

    //Consultar productos de un usuario
    Set<Producto> findByUsuario(long idUsuario);

    //Consultar productos con más puntos. List porque puede haber repetidos
    List<Producto> findTopPuntos();

    //Añadir producto
    Producto addProducto(ProductoDTO productoDTO);
}
