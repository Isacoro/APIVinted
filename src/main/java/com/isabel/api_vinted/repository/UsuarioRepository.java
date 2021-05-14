package com.isabel.api_vinted.repository;

import com.isabel.api_vinted.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    //Todos
    Set<Usuario> findAll();

    //Por email y password
    Optional<Usuario> findByEmailAndPassword(String email, String password);
}
