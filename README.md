# VaxService API & Service Suite

Servicio backend en Java / Spring Boot para la gestión del sistema de vacunación (**VaxService**), listo para desplegar mediante **Docker** y **Docker Compose**.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17+ (o Java 11)
* **Framework:** Spring Boot / Spring Framework
* **Pruebas:** JUnit 5, Spring Boot Test (`@Autowired`, `@BeforeEach`, `@Test`)
* **Contenerización:** Docker & Docker Compose
* **Base de Datos:** MongoDB / PostgreSQL (según la configuración de entorno)

---

## 🚀 Arquitectura y Pruebas Unitarias

El proyecto cuenta con suites de pruebas integración y unitarias para verificar la consistencia del servicio de pacientes (`VaxServiceTestCase`).

### Ejemplo de Caso de Prueba (`VaxServiceTestCase.java`)

```java
@SpringBootTest
public class VaxServiceTestCase {
    private Date dob;

    @Autowired
    VaxService service;

    @BeforeEach
    public void setUp() throws VaxException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 17);
        dob = cal.getTime();
    }

    @Test
    public void testCreatePatient() throws VaxException {
        Patient fede = this.service.createPatient("federico.orlando@info.unlp.edu.ar", "Federico Orlando", "pas$w0rd", dob);
        assertNotNull(fede.getId());
        assertEquals("Federico Orlando", fede.getFullname());

        Optional<Patient> us = this.service.getPatientByEmail("federico.orlando@info.unlp.edu.ar");
        if (!us.isPresent()) {
            throw new VaxException("Patient doesn't exist");
        }

        Patient user = us.get();
        assertNotNull(user.getId());
        assertEquals("Federico Orlando", user.getFullname());
        assertEquals(dob, user.getDayOfBirth());
        assertEquals("pas$w0rd", user.getPassword());

        // Verificación de violación de restricciones (Email único)
        VaxException ex = assertThrows(VaxException.class, () -> 
            this.service.createPatient("federico.orlando@info.unlp.edu.ar", "Federico Orlando", "pas$w0rd", dob)
        );
        assertEquals("Constraint Violation", ex.getMessage());
    }
}
```

---

## 🐳 Estructura de Dockerización

Para empaquetar y levantar la aplicación junto con sus dependencias, debes contar con los siguientes archivos en la raíz del proyecto:

### 1. `Dockerfile`

```dockerfile
# Etapa 1: Construcción (Build)
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### 2. `docker-compose.yml`

```yaml
version: '3.8'

services:
  app:
    build: .
    container_name: vaxservice-app
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - MONGO_HOST=mongodb
      - MONGO_PORT=27017
      - MONGO_DB=vaccination
    depends_on:
      - mongodb
    restart: always

  mongodb:
    image: mongo:latest
    container_name: vaxservice-db
    ports:
      - "27017:27017"
    volumes:
      - mongo-data:/data/db
    restart: always

volumes:
  mongo-data:
```

---

## 💻 Instrucciones de Despliegue y Ejecución

### Prerrequisitos
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y corriendo.
* [Git](https://git-scm.com/) (opcional).

### Pasos para Levantar el Proyecto con Docker Compose

1. **Clonar o descargar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd vaxservice
   ```

2. **Construir e iniciar los contenedores:**
   ```bash
   docker-compose up -d --build
   ```

3. **Verificar estado de los contenedores:**
   ```bash
   docker-compose ps
   ```

4. **Ver los logs de la aplicación Spring Boot:**
   ```bash
   docker-compose logs -f app
   ```

5. **Detener la aplicación y los servicios:**
   ```bash
   docker-compose down
   ```

---

## 🧪 Ejecutar Pruebas (Tests) dentro de Docker

Para correr las pruebas unitarias e integraciones de JUnit dentro del contenedor del builder:

```bash
docker run --rm -v $(pwd):/app -w /app maven:3.9-eclipse-temurin-17 mvn test
```