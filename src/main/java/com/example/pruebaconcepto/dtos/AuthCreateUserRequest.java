package com.example.pruebaconcepto.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @NotBlank @Email String email,
                                    @Valid AuthCreateRolRequest rolesRequest) {


}
