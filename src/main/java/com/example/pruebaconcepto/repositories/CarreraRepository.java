package com.example.pruebaconcepto.repositories;

import com.example.pruebaconcepto.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}
