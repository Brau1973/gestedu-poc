package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuario.getRoles().forEach(rol -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getNombre().name())));
        });

        usuario.getRoles().stream()
                .flatMap(rol -> rol.getPermisos().stream())
                .forEach(permiso -> {
                    authorityList.add(new SimpleGrantedAuthority(permiso.getNombre()));
        });

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                usuario.getIsEnable(),
                usuario.getAccountNonExpired(),
                usuario.getCredentialsNonExpired(),
                usuario.getAccountNonLocked(),
                authorityList);

    }
}
