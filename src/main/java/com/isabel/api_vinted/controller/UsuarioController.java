package com.isabel.api_vinted.controller;

import com.isabel.api_vinted.domain.Usuario;
import com.isabel.api_vinted.domain.UsuarioDTO;
import com.isabel.api_vinted.exception.UsuarioNotFoundException;
import com.isabel.api_vinted.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    /**** LISTADO DE TODOS LOS USUARIOS ****/

    @GetMapping(value = "/usuarios", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarios() {
        logger.info("Inicio getUsuarios");
        Set<Usuario> usuarios = usuarioService.findAll();
        logger.info("Fin getUsuarios");
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }


    /**** BUSCAR USUARIO POR EMAIL Y PASSWORD ****/

    @GetMapping(value = "/usuarios/email-password", produces = "application/json")
    public ResponseEntity<Usuario> getUsuario(@RequestParam(value = "email", defaultValue = "") String email,
                                              @RequestParam(value = "password", defaultValue = "") String password) {
        logger.info("Inicio getUsuario");
        Usuario usuario = usuarioService.findByEmailAndPassword(email, password);
        logger.info("Fin getUsuario");
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    /**** LISTAR LOS USUARIOS CON MÁS VENTAS ****/

    @GetMapping(value = "/usuarios/top", produces = "application/json")
    public ResponseEntity<List<Usuario>> getUsuariosTop() {
        logger.info("Inicio getUsuariosTop");
        List<Usuario> usuariosTop = usuarioService.findByTopVentas();
        logger.info("Fin getUsuariosTop");
        return new ResponseEntity<>(usuariosTop, HttpStatus.OK);
    }

    /**** AÑADIR USUARIO ****/

    @PostMapping(value = "/usuarios", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("Inicio addUsuario");
        Usuario addedUsuario = usuarioService.addUsuario(usuarioDTO);
        logger.info("Fin addUsuario");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUsuario);
    }

    /**** EXCEPCIONES EN CASO DE NO ENCONTRAR EL ELEMENTO ****/

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(UsuarioNotFoundException pnfe) {
        Response response = Response.errorResponse(Response.NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
