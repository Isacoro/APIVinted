package com.isabel.api_vinted.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private float precio;

    @Column
    private int puntos;

    @Column
    private String categoria;

    @Column
    private String url;

    //N Productos 1 usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "usuario")
    private Usuario usuario;
}
