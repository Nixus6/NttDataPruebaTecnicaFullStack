# Usar JDK como base
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/clientes.jar app.jar

# Exponer el puerto
EXPOSE 8090

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
