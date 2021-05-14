package com.isabel.api_vinted.service;

import com.isabel.api_vinted.domain.Usuario;
import com.isabel.api_vinted.domain.UsuarioDTO;
import com.isabel.api_vinted.exception.UsuarioNotFoundException;
import com.isabel.api_vinted.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Set<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByEmailAndPassword(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UsuarioNotFoundException(email + " " + password));
    }

    @Override
    public Usuario addUsuario(UsuarioDTO usuarioDTO) {

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioDTO.getNombre());
        nuevoUsuario.setEmail(usuarioDTO.getEmail());
        nuevoUsuario.setPassword(usuarioDTO.getPassword());

        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public List<Usuario> findByTopVentas() {
        return usuarioRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Usuario::getVentas).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
