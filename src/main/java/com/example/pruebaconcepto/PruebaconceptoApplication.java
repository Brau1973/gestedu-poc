package com.example.pruebaconcepto;

import com.example.pruebaconcepto.dtos.RolEnum;
import com.example.pruebaconcepto.models.Permiso;
import com.example.pruebaconcepto.models.Rol;
import com.example.pruebaconcepto.models.Usuario;
import com.example.pruebaconcepto.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class PruebaconceptoApplication {

	public static void main(String[] args) {
		run(PruebaconceptoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			Permiso crearPermiso = Permiso.builder()
					.nombre("Crear")
					.build();
			Permiso leerPermiso = Permiso.builder()
					.nombre("Leer")
					.build();
			Permiso actualizarPermiso = Permiso.builder()
					.nombre("Actualizar")
					.build();
			Permiso eliminarPermiso = Permiso.builder()
					.nombre("Eliminar")
					.build();

			Rol	adminRol = Rol.builder()
					.nombre(RolEnum.ADMIN)
					.permisos(Set.of(crearPermiso, leerPermiso, actualizarPermiso, eliminarPermiso))
					.build();
			Rol estudianteRol = Rol.builder()
					.nombre(RolEnum.ESTUDIANTE)
					.permisos(Set.of(leerPermiso))
					.build();
			Rol profesorRol = Rol.builder()
					.nombre(RolEnum.PROFESOR)
					.permisos(Set.of(crearPermiso, leerPermiso, actualizarPermiso))
					.build();
			Rol invitadoRol = Rol.builder()
					.nombre(RolEnum.INVITADO)
					.permisos(Set.of(leerPermiso))
					.build();

			Usuario admin = Usuario.builder()
					.username("admin")
					.password("1234")
					.email("correo@correo")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(adminRol))
					.build();
			Usuario estudiante = Usuario.builder()
					.username("estudiante")
					.password("1234")
					.email("correo@correo")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(estudianteRol))
					.build();
			Usuario profesor = Usuario.builder()
					.username("profesor")
					.password("1234")
					.email("correo@correo")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(profesorRol))
					.build();
			Usuario invitado = Usuario.builder()
					.username("invitado")
					.password("1234")
					.email("correo@correo")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(invitadoRol))
					.build();

			usuarioRepository.saveAll(List.of(admin, estudiante, profesor, invitado));
		};
	}
}
