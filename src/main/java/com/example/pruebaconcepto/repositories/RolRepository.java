package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {

    List<Rol> findRolsByNombreIn(List<String> rolesList);
}
