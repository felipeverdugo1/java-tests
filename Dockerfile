# Etapa 1: Build y descarga de dependencias en caché
FROM maven:3.8.6-eclipse-temurin-8 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean test-compile

# Etapa 2: Entorno de Ejecución de Tests (con Maven y JDK)
FROM maven:3.8.6-eclipse-temurin-8
WORKDIR /app
COPY --from=builder /root/.m2 /root/.m2
COPY --from=builder /app /app

ENV MYSQL_HOST=mysql-db

CMD ["mvn", "test"]