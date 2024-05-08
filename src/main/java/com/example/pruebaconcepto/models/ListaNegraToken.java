package com.example.pruebaconcepto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class ListaNegraToken {

    @Id
    private String token;

    private Instant fechaExpiracion;
}
