package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.TokenPass;
import com.example.pruebaconcepto.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenPassRepository extends CrudRepository<TokenPass, Long> {

    Optional<TokenPass> findByToken(String token);

}
