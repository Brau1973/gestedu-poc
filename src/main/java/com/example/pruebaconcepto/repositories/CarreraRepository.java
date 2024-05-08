package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarreraRepository extends JpaRepository<Carrera, Long>, JpaSpecificationExecutor<Carrera> {
    boolean existsByNombre(String nombre);
}
