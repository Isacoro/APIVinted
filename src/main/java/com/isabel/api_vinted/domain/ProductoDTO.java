package com.isabel.api_vinted.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {

    private String nombre;
    private String descripcion;
    private float precio;
    private String categoria;
    private String url;
}
