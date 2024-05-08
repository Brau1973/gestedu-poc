package com.example.pruebaconcepto.dtos;

import com.example.pruebaconcepto.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String ci;
    private String nombre;
    private String apellido;
    @Email
    private String email;
    private String password;
    private String telefono;
    private String domicilio;
    private String fechaNac;
    
}
