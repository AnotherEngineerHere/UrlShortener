# URL Shortener

Este proyecto es una aplicación de acortador de URLs construida en Java usando Spring Boot. Permite a los usuarios ingresar una URL larga y obtener una versión corta que redirige a la URL original. La aplicación también incluye persistencia en una base de datos MySQL y está completamente dockerizada.

## Características

- **Acortar URLs**: Genera una versión corta de una URL larga.
- **Redirección**: Redirige la URL acortada a la URL original.
- **Persistencia**: Almacena las URLs en una base de datos MySQL.
- **Dockerización**: La aplicación y la base de datos se ejecutan en contenedores Docker.

## Requisitos Previos

- **Java 17** o superior
- **Maven 3.8+**
- **Docker** y **Docker Compose**

## Configuración del Proyecto

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/url-shortener.git
   cd url-shortener
   ```

2. **Construir el proyecto:**

   Asegúrate de estar en el directorio raíz del proyecto y ejecuta:

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación localmente:**

   Puedes ejecutar la aplicación localmente sin Docker usando:

   ```bash
   mvn spring-boot:run
   ```

4. **Dockerizar la aplicación:**

   Para ejecutar la aplicación y la base de datos en contenedores Docker, sigue estos pasos:

   - **Compilar la aplicación:**

     ```bash
     mvn clean package
     ```

   - **Levantar los contenedores:**

     ```bash
     docker-compose up --build
     ```

   La aplicación estará disponible en `http://localhost:8080`.

## Conectarse a la Base de Datos MySQL

La base de datos MySQL se ejecuta en un contenedor Docker y está disponible en `localhost:3306`. Puedes conectarte a ella usando un cliente MySQL o desde tu IDE con las siguientes credenciales:

- **Host**: `localhost`
- **Port**: `3306`
- **Database**: `urlshortener`
- **Username**: `user`
- **Password**: `userpassword`

## Pruebas Unitarias

Este proyecto incluye pruebas unitarias utilizando JUnit y Mockito. Para ejecutar las pruebas, usa el siguiente comando:

```bash
mvn test
```

## API Endpoints

- **Acortar una URL:**
  - **URL**: `/api/v1/shorten`
  - **Método**: `POST`
  - **Cuerpo del Request**:
    ```json
    {
      "originalUrl": "https://example.com"
    }
    ```
  - **Respuesta**:
    ```json
    {
      "shortUrl": "http://localhost:8080/abc123",
      "originalUrl": "https://example.com",
      "createdAt": "2024-08-11T10:00:00",
      "expiresAt": "2024-09-10T10:00:00"
    }
    ```

- **Redirigir a la URL original:**
  - **URL**: `/abc123`
  - **Método**: `GET`
  - **Descripción**: Redirige a la URL original.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o envía un pull request.

