package com.isabel.api_vinted.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String ciudad;

    @Column
    private int ventas;

    @Column
    private int puntos;

    //1 usuario N productos
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Producto> productos;
}
