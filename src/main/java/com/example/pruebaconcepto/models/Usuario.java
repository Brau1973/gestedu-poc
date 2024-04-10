package com.example.pruebaconcepto.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    private String tokenPassword;

    @Column(name= "is_enable")
    private Boolean isEnable;

    @Column (name= "account_non_expired")
    private Boolean accountNonExpired;

    @Column (name= "account_non_locked")
    private Boolean accountNonLocked;

    @Column (name= "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
}
