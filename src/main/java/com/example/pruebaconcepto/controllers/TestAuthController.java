package com.example.pruebaconcepto.controllers;

import com.example.pruebaconcepto.dtos.RolEnum;
import com.example.pruebaconcepto.dtos.UsuarioDTO;
import com.example.pruebaconcepto.models.Rol;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/helloSecure")
    @PreAuthorize("hasAuthority('Leer')")
    public String helloSecure() {
        return "Hello World secure!";
    }

    @RequestMapping("/helloSecureProfe")
    @PreAuthorize("hasRole('PROFESOR')")
    public String helloSecureProf() {
        return "Hello World PROFE!";
    }

    @GetMapping("/helloSecureAdmin")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public String helloSecureAdmin() {
        return "Hello World Admin!";
    }

    @PostMapping("/crearUsuario")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        Set<Rol> roles = usuarioDTO.getRoles().stream()
                .map(rol -> Rol.builder()
                        .nombre(RolEnum.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .email(usuarioDTO.getEmail())
                .roles(roles)
                .build();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/borrarUsuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String borrarUsuario(@RequestParam String id) {
        usuarioRepository.deleteById(Long.parseLong(id));
        return "Usuario borrado";
    }
}
