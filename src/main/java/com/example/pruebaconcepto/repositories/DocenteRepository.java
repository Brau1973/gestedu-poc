package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DocenteRepository extends JpaRepository<Docente, Long>, JpaSpecificationExecutor<Docente> {
    boolean existsByDocumento(String documento);
}
