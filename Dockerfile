# Utiliza una imagen base de Java
FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación y su archivo .env (si es necesario)
COPY target/*.jar /app/app.jar

# Expone el puerto en el que la aplicación escuchará las solicitudes
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
