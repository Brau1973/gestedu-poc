package com.example.pruebaconcepto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.pruebaconcepto.models.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsernameOrEmail(String username, String email);

    Optional<Usuario> findByTokenPassword(String tokenPassword);

}
