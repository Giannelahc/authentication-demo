# Usar una imagen base de Java 11
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el jar del proyecto (asegúrate de construirlo previamente)
COPY target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 9090

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
