package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.exceptions.TokenInactivoException;
import com.example.pruebaconcepto.exceptions.TokenInvalidoException;
import com.example.pruebaconcepto.exceptions.TokenVencidoException;
import com.example.pruebaconcepto.models.TokenPass;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.TokenPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenPassService {

    @Autowired
    TokenPassRepository tokenPassRepository;

    public String crearTokenPassword(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        Date vencimiento = new Date();
        vencimiento.setTime(vencimiento.getTime() + 3600000); // 1 hora

        TokenPass tokenPass = new TokenPass();
        tokenPass.setToken(token);
        tokenPass.setVencimiento(vencimiento);
        tokenPass.setActivo(true);
        tokenPass.setUsuario(usuario);

        tokenPassRepository.save(tokenPass);

        return token;
    }

    public Optional<Usuario> validarToken(String token) {
        Optional<TokenPass> tokenPass = tokenPassRepository.findByToken(token);
        if (!tokenPass.isPresent()) {
            throw new TokenInvalidoException("El tokenPass no es válido.");
        }
        if (!tokenPass.get().getActivo()) {
            throw new TokenInactivoException("El tokenPass no está activo");
        }
        if (tokenPass.get().getVencimiento().before(new Date())) {
            throw new TokenVencidoException("El tokenPass ha vencido");
        }
        return Optional.ofNullable(tokenPass.get().getUsuario());
    }

    public void invalidarToken(String token) {
        Optional<TokenPass> tokenPass = tokenPassRepository.findByToken(token);
        if (tokenPass.isPresent()) {
            tokenPass.get().setActivo(false);
            tokenPassRepository.save(tokenPass.get());
        }
    }

}
