package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.ListaNegraToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ListaNegraTokenRepository extends CrudRepository<ListaNegraToken, String> {

    ListaNegraToken findByToken(String token);
}
