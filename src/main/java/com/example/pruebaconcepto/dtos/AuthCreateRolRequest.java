package com.example.pruebaconcepto.dtos;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRolRequest (
        @Size(max=3, message = "No puede tener más de 3 roles.") List<String> rolesList){
}
