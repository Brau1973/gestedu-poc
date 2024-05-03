# gestedu-poc
Pruebas de concepto para proyecto final tecnologo informatico (Java Spring)

<<<<<<< HEAD
## Endpoints

### Login

- **URL**: `/login`
- **Método**: `POST`
- **Enviar JSON**:
```json
{
  "email": "correo@correo",
  "password": "password"
}
```
### Logout

- **URL**: `/logout`
- **Método**: `POST`
- **Enviar token JWT como Bearer.**

### Registro para invitado

- **URL**: `/usuario/registro`
- **Método**: `POST`
- **Enviar JSON.**
- Siempre registra como estudiante.
```json
{
    "nombre": "nombre",
    "apellido": "apellido",
    "email": "correo@unico",
    "password": "password",
    "ci": "1234567",
    "telefono": "099099099",
    "domicilio": "dire",
    "tipoUsuario": "COORDINADOR"
}
```
### Registro otros usuarios para ADMINISTRADOR

- **URL**: `/usuario/registro/admin`
- **Método**: `POST`
- **Enviar el mismo JSON anterior.**

### Solicitud reseteo contraseña
- **URL**: `/usuario/resetPassword`
- **Método**: `POST`
- **Enviar JSON.**
- Se enviará correo electrónico con token.
```json
{
  "mailTo": "email"
}
```
### Cambiar contraseña con token
- **URL**: `/usuario/cambiarPassword`
- **Método**: `POST`
- **Enviar JSON.**
```json
{
  "password": "nuevaPassword",
  "confirmPassword": "nuevaPassword",
  "tokenPassword": "token en el link del correo"
}
```
=======

- Incorporación de Spring Security
- Implementación de JWT
- Restablecimiento de contraseña por correo electrónico
>>>>>>> 696b2b793944635fd6c7de8d63da1178fec524b8
