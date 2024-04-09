package com.example.pruebaconcepto.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest (@NotBlank String username,
                                @NotBlank String password){
}
