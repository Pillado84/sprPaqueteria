# 📦 sprPaqueteria (Spring Boot)

Aplicación **Spring Boot** para la **gestión de paquetería** con persistencia en **SQLite**. El proyecto está configurado con **Java 25** y expone una API principalmente a través de **Spring Data REST**, además de un controlador propio para consultar valores de enums.

---

## ✅ Qué incluye

* **Spring Boot 4.0.2** (Maven)
* Persistencia con **Spring Data JPA** sobre **SQLite**
* Exposición automática de recursos con **Spring Data REST**
* Validación con `jakarta.validation`
* Entidades con **Lombok** (`@Data`, `@Builder`, etc.)
* Endpoints auxiliares para enums (tipos/estados)

---

## 🧱 Estructura real del proyecto

```
sprPaqueteria/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/demo/
│       │       ├── SprPaqueteriaApplication.java
│       │       ├── controllers/
│       │       │   └── EnumController.java
│       │       ├── entities/
│       │       │   ├── Client.java
│       │       │   ├── Return.java
│       │       │   ├── Sending.java
│       │       │   ├── Transport.java
│       │       │   └── User.java
│       │       └── repositories/
│       │           ├── ClientRepository.java
│       │           ├── ReturnRepository.java
│       │           ├── SendingRepository.java
│       │           ├── TransportRepository.java
│       │           └── UserRepository.java
│       └── resources/
│           └── application.properties
└── (carpetas de build/IDE)
```

> Nota: en esta versión no hay paquetes `service`/`dto`; la capa API se apoya en **Spring Data REST** (repositorios expuestos automáticamente).

---

## 🗄️ Configuración de base de datos (SQLite)

En `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:sqlite:paqueteria.db
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.data.rest.base-path=/api/v1
```

* El fichero `paqueteria.db` se crea en el **working directory** (directorio desde el que ejecutas la app).
* El esquema se gestiona con `hibernate.ddl-auto=update`.

---

## 🧩 Modelo (entidades)

* `Sending` (tabla `envios`): código de seguimiento único, tipo y estado (`enum`), fechas del flujo (creación, recogida, almacén, entrega, cancelación), relaciones con `Transport` y `Client`.
* `Transport`: vehículo y estado (enums), además de campos como `matricula` (se usa en consultas del repositorio).
* `Client`: cliente.
* `User`: usuario con rol (`enum`).
* `Return`: devoluciones con estado (`enum`).

---

## 🌐 API (Spring Data REST)

La base path está configurada en:

* `spring.data.rest.base-path=/api/v1`

### Recursos expuestos por repositorios

Los repositorios están anotados con `@RepositoryRestResource`, por lo que se publican automáticamente:

* `/api/v1/envios` → `SendingRepository` (incluye `existsByCodigoSeguimiento(...)`)
* `/api/v1/transportes` → `TransportRepository` (incluye `findByMatricula(...)`)
* `/api/v1/clientes` → `ClientRepository`
* `/api/v1/usuarios` → `UserRepository`
* `/api/v1/devoluciones` → `ReturnRepository`

> Spring Data REST ofrece operaciones CRUD estándar (GET/POST/PUT/PATCH/DELETE) para estos recursos.

### Endpoints de enums

Controlador: `EnumController`.

* `GET /api/v1/enums/tipos-vehiculo`
* `GET /api/v1/enums/estados-transporte`
* `GET /api/v1/enums/tipos-envios`
* `GET /api/v1/enums/estados-envios`
* `GET /api/v1/enums/roles-usuarios`
* `GET /api/v1/enums/estados-devoluciones`

---

## ▶️ Ejecutar el proyecto

### Requisitos

* **Java 25**
* **Maven**

### Comandos

```bash
mvn spring-boot:run
```

O bien ejecutar la clase:

* `com.example.demo.SprPaqueteriaApplication`

---

## ⚠️ Warnings de SQLite en Java 21+

Si al ejecutar ves warnings sobre `System::load` (acceso nativo), añade este VM arg:

```
--enable-native-access=org.xerial.sqlitejdbc
```

---

## 📌 Nota sobre “histórico”

El diseño funcional del proyecto está orientado a **conservar el histórico** de envíos. Aunque Spring Data REST puede exponer `DELETE` por defecto, el flujo esperado es **crear, consultar y actualizar** (por ejemplo, cambiar estados y registrar fechas).

---

## 🛠️ Posibles mejoras

* Añadir capa `service` para reglas de negocio (transiciones de estado, finalización, etc.)
* Deshabilitar `DELETE` en repositorios si se quiere imponer el histórico a nivel API
* Documentación OpenAPI/Swagger
* Tests de integración

---

## 👤 Autor

Proyecto académico con Spring Boot, JPA, validación y SQLite.
