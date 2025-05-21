# AgendaUsuarios 📒🔐

Proyecto backend en Java usando Spring Boot para la gestión de usuarios y autenticación mediante JWT. Permite registrar usuarios, iniciar sesión y gestionar entidades protegidas (como tareas o agendas) usando tokens.

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Maven
- Git
- Postman (para pruebas)

---

## 🚀 Funcionalidades principales

- Login con usuario y contraseña.
- Generación de token JWT tras autenticación.
- Validación del token en peticiones protegidas.
- Gestión de tareas o agendas (CRUD).
- Protección de endpoints según autenticación.

---

## 🧪 Prueba rápida con Postman

### 1. Login
**POST** `/auth/login`  
**Body:**

```json
{
  "username": "usuario",
  "password": "contraseña"
}