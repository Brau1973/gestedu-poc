package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.dtos.AuthLoginRequest;
import com.example.pruebaconcepto.dtos.AuthResponse;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.UsuarioRepository;
import com.example.pruebaconcepto.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public AuthResponse loginUser(Usuario usuario) {
        return new AuthResponse(usuario.getUsername(), "Usuario logueado", "jwt", true);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        //generar token de acceso
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.autenticar(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.crearToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "Usuario logueado", token, true);
        return authResponse;
    }

    public Authentication autenticar(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Usuario no encontrado");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
