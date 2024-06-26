package com.example.pruebaconcepto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {
    private Long id;
    String nombre;
    String descripcion;
    Integer creditos;
    Long carreraId;
    Long docenteId;
}
