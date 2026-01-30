<div>

# 📚 API Gestion de librería

 Es una API REST robusta y escalable desarrollada con **Java 17** y **Spring Boot 3**, diseñada para gestionar de manera eficiente el inventario, usuarios y transacciones de una librería moderna.

La aplicación implementa autenticación **OAuth2 con Google**, control de acceso basado en roles, y un sistema completo de gestión que incluye:

- 📖 **Catálogo de libros** con operaciones CRUD completas
- 👥 **Administración de usuarios** con roles diferenciados
- 🛒 **Sistema de compras** con trazabilidad completa
- 🔐 **Seguridad robusta** mediante JWT y OAuth2

---

## ✨ Características

### 🔹 Gestión de Libros
- ✅ CRUD completo (Crear, Leer, Actualizar, Eliminar)
- ✅ Consultas paginadas para rendimiento óptimo
- ✅ Búsqueda y filtrado de catálogo

### 🔹 Administración de Usuarios
- ✅ Registro y autenticación con Google OAuth2
- ✅ Gestión de perfiles (solo administradores)
- ✅ Sistema de roles: `ADMIN` y `USER`
- ✅ Creación automática de usuarios nuevos

### 🔹 Sistema de Compras
- ✅ Usuarios pueden crear y consultar sus propias compras
- ✅ Administradores tienen acceso total a todas las transacciones
- ✅ Historial completo de compras por usuario
- ✅ Validaciones de negocio personalizadas

### 🔹 Seguridad Avanzada
- 🔒 Autenticación **JWT** + **OAuth2 Resource Server**
- 🔒 Inicio de sesión con **Google**
- 🔒 Conversión automática de tokens de Google a tokens internos
- 🔒 Protección de endpoints basada en roles
- 🔒 Manejo centralizado de excepciones

### 🔹 Calidad y Validaciones
- ⚡ Validaciones con **Jakarta Validation**
- ⚡ Excepciones personalizadas (`NotFoundException`, `BadRequestException`)
- ⚡ Mensajes de error descriptivos
- ⚡ Arquitectura limpia y escalable

---

## 🛠 Tecnologías

<table>
<tr>
<td>

**Backend**
- Java 17
- Spring Boot 3.x
- Maven

</td>
<td>

**Seguridad**
- Spring Security
- OAuth2 Resource Server
- JWT (JSON Web Tokens)

</td>
<td>

**Base de Datos**
- MySQL 8.0
- JPA / Hibernate
- Spring Data JPA

</td>
</tr>
</table>

---

## 🚀 Instalación

### Prerrequisitos

- **Java 17** o superior
- **Maven 3.8+**
- **MySQL 8.0+**
- Cuenta de **Google Cloud** (para OAuth2)

### Paso 1: Clonar el repositorio

```bash
git clone https://github.com/tuusuario/BookStore.git
cd BookStore
```

### Paso 2: Configurar Base de Datos

Crea una base de datos en MySQL:

```sql
CREATE DATABASE books_store;
```

Configura el archivo `application.properties`:

```properties
# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/books_store?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Configuración de OAuth2 Resource Server
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://accounts.google.com
```

### Paso 3: Configurar Google OAuth2

1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un proyecto nuevo o selecciona uno existente
3. Habilita la **Google+ API**
4. Crea credenciales OAuth2 (ID de cliente)
5. Agrega `http://localhost:8080` a las URLs autorizadas

> Los valores `Client ID` y `Client Secret` solo son necesarios en el frontend o en Postman, no en Spring Boot.

### Paso 4: Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

---

## 📡 Endpoints

### 👤 Usuarios `/usuario`

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| `POST` | `/crearUsuario` | Crear nuevo usuario | 🌐 Público |
| `GET` | `/obtenerUsuarios` | Listar todos los usuarios | 🔑 ADMIN |
| `GET` | `/obtenerUsuario/{id}` | Obtener usuario por ID | 🔑 ADMIN |
| `PUT` | `/editarUsuario/{id}` | Actualizar usuario | 🔑 ADMIN |
| `DELETE` | `/eliminarUsuario/{id}` | Eliminar usuario | 🔑 ADMIN |

### 📖 Libros `/libro`

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| `POST` | `/crearLibro` | Agregar nuevo libro | 🔑 ADMIN |
| `GET` | `/obtenerLibros` | Listar todos los libros | 🔑 ADMIN/USER |
| `GET` | `/obtenerLibro/{id}` | Obtener libro por ID | 🔑 ADMIN/USER |
| `PUT` | `/editarLibro/{id}` | Actualizar libro | 🔑 ADMIN |
| `DELETE` | `/eliminarLibro/{id}` | Eliminar libro | 🔑 ADMIN |

### 🛒 Compras `/compra`

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| `POST` | `/crearCompra` | Registrar nueva compra | 🔑 ADMIN/USER |
| `GET` | `/obtenerCompras` | Listar todas las compras | 🔑 ADMIN |
| `GET` | `/user/obtenerCompras` | Listar compras del usuario actual | 🔑 USER |
| `GET` | `/obtenerCompra/{id}` | Obtener compra por ID | 🔑 ADMIN |
| `GET` | `/user/obtenerCompra/{id}` | Obtener compra por ID (usuario) | 🔑 USER |

---

## 🔐 Seguridad

### Flujo de Autenticación

```mermaid
graph LR
    A[Usuario] -->|Login Google| B[Google OAuth2]
    B -->|JWT Token| C[BookStore API]
    C -->|Validación| D[Spring Security]
    D -->|Rol: ADMIN/USER| E[Acceso a Recursos]
```

### Características de Seguridad

- 🔒 **JWT (JSON Web Tokens):** Tokens firmados y verificados
- 🔒 **OAuth2 Resource Server:** Integración nativa con Google
- 🔒 **Control de Acceso Basado en Roles (RBAC):**
  - `ADMIN`: Acceso completo al sistema
  - `USER`: Acceso a libros y sus propias compras
- 🔒 **Registro Automático:** Usuarios nuevos se crean automáticamente con rol `USER`

### Probar con Postman

1. **Obtener Token de Google:**
   - En Postman: `Authorization` → `OAuth 2.0` → `Get New Access Token`
   - Ingresa tu `Client ID` y `Client Secret`
   - Completa el flujo de autenticación con Google

2. **Usar el Token:**
   ```
   Authorization: Bearer <tu_token_aqui>
   ```

3. **Ejemplo de Petición:**
   ```http
   GET /libro/obtenerLibros HTTP/1.1
   Host: localhost:8080
   Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...
   ```

---

## 📋 Ejemplos de Uso

### Crear un Libro (ADMIN)

```bash
curl -X POST http://localhost:8080/libro/crearLibro \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Cien años de soledad",
    "autor": "Gabriel García Márquez",
    "precio": 25.99,
    "stock": 50
  }'
```

### Crear una Compra (USER)

```bash
curl -X POST http://localhost:8080/compra/crearCompra \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "libroId": 1,
    "cantidad": 2
  }'
```

### Obtener Compras del Usuario (USER)

```bash
curl -X GET http://localhost:8080/compra/user/obtenerCompras \
  -H "Authorization: Bearer <token>"
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto:

1. Haz un fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Agrega nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la licencia **Creative Commons Attribution 4.0 International (CC BY 4.0)**.

**Esto significa que puedes:**
- ✅ Usar el proyecto libremente
- ✅ Modificar el código
- ✅ Distribuir el proyecto
- ✅ Usar con fines comerciales

**Siempre que:**
- 📝 Des crédito al desarrollador original
- 📝 Proporciones un enlace a la licencia
- 📝 Indiques si se hicieron cambios

[![License: CC BY 4.0](https://licensebuttons.net/l/by/4.0/88x31.png)](https://creativecommons.org/licenses/by/4.0/)

Más información: [CC BY 4.0 Legal Code](https://creativecommons.org/licenses/by/4.0/legalcode)
