# Usa una imagen base de OpenJDK
FROM eclipse-temurin:21-jdk-alpine

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/urlshortener-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escuchará
EXPOSE 3100

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
