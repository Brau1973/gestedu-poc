package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.dtos.CarreraDTO;
import com.example.pruebaconcepto.dtos.UsuarioDTO;
import com.example.pruebaconcepto.exceptions.ResourceNotFoundException;
import com.example.pruebaconcepto.models.Carrera;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByCi(String ci) {
        return usuarioRepository.findByCi(ci);
    }

    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
