package com.example.pruebaconcepto.controllers;

import com.example.pruebaconcepto.dtos.ChangePasswordDTO;
import com.example.pruebaconcepto.dtos.EmailValuesDTO;
import com.example.pruebaconcepto.dtos.UsuarioDTO;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.services.EmailService;
import com.example.pruebaconcepto.services.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @GetMapping("/send")
    public ResponseEntity<?> sendEmail() {
        emailService.sendEmail();
        return new ResponseEntity<>("Correo enviado con éxito", HttpStatusCode.valueOf(200));
    }

    @PostMapping("/emailPassword")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {

        Optional<Usuario> usuario = usuarioService.getByUsernameOrEmail(dto.getUsername(), dto.getMailTo());

        if(usuario.isEmpty()) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        Usuario user = usuario.get();
        dto.setMailFrom(emailFrom);
        dto.setMailTo(user.getEmail());
        dto.setMailSubject("Recuperar de contraseña");
        dto.setUsername(user.getUsername());

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        user.setTokenPassword(token);
        usuarioService.updateUsuario(user);
        dto.setTokenPassword(token);
        emailService.sendEmailTemplate(dto);
        return new ResponseEntity<>("Correo enviado con éxito", HttpStatusCode.valueOf(200));
    }

    @PostMapping("/cambiarPassword")
    public ResponseEntity<?> cambiarPassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Datos incorrectos", HttpStatus.BAD_REQUEST);
        }
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            return new ResponseEntity<>("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
        }
        Optional<Usuario> usuario = usuarioService.getByTokenPassword(dto.getTokenPassword());
        if(usuario.isEmpty()){
            return new ResponseEntity<>("Token no encontrado", HttpStatus.NOT_FOUND);
        }
        Usuario user = usuario.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(newPassword);
        user.setTokenPassword(null);
        usuarioService.updateUsuario(user);

        return new ResponseEntity<>("Contraseña actualizada.", HttpStatus.OK);
    }
      }
