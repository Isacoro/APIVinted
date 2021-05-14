package com.isabel.api_vinted.service;

import com.isabel.api_vinted.domain.Producto;
import com.isabel.api_vinted.domain.ProductoDTO;
import com.isabel.api_vinted.exception.UsuarioNotFoundException;
import com.isabel.api_vinted.repository.ProductoRepository;
import com.isabel.api_vinted.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Buscar todos
    @Override
    public Set<Producto> findAll() {
        return productoRepository.findAll();
    }

    //Buscar por categoría
    @Override
    public Set<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    //Buscar por usuario
    @Override
    public Set<Producto> findByUsuario(long idUsuario) {
        return productoRepository.findByUsuario(usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new UsuarioNotFoundException(idUsuario)));
    }

    //Buscar los 10 productos con más puntos
    @Override
    public List<Producto> findTopPuntos() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getPuntos).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    //Añadir producto
    @Override
    public Producto addProducto(ProductoDTO productoDTO) {

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoDTO.getNombre());
        nuevoProducto.setDescripcion(productoDTO.getDescripcion());
        nuevoProducto.setPrecio(productoDTO.getPrecio());
        nuevoProducto.setCategoria(productoDTO.getCategoria());
        nuevoProducto.setUrl(productoDTO.getUrl());

        return productoRepository.save(nuevoProducto);
    }
}
