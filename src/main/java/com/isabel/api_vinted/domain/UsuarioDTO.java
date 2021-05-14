package com.isabel.api_vinted.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String email;
    private String password;
}
