package com.isabel.api_vinted.repository;

import com.isabel.api_vinted.domain.Producto;
import com.isabel.api_vinted.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    //Consultar todos
    Set<Producto> findAll();

    //Consultar por categoria
    Set<Producto> findByCategoria(String categoria);

    //Consultar por usuario
    Set<Producto> findByUsuario(Usuario idUsuario);
}
