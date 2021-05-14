package com.isabel.api_vinted.service;

import com.isabel.api_vinted.domain.Usuario;
import com.isabel.api_vinted.domain.UsuarioDTO;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    //Consultar todos
    Set<Usuario> findAll();

    //Consultar por email y password
    Usuario findByEmailAndPassword(String email, String password);

    //Crear nuevo usuario (Registro)
    Usuario addUsuario(UsuarioDTO usuarioDTO);

    //Top usuario con más ventas
    List<Usuario> findByTopVentas();
}
