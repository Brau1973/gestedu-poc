package com.example.pruebaconcepto.dtos.carrera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarreraDTO {
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private Integer creditos;
}