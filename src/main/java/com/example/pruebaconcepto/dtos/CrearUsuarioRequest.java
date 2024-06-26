package com.example.pruebaconcepto.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearUsuarioRequest {
    @NotBlank
    private String ci;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String telefono;
    private String domicilio;
    private Date fechaNac;
    private TipoUsuario tipoUsuario;
}
