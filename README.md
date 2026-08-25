# BD2 2022 - VaxService (UNLP)

Proyecto backend para la materia **Bases de Datos 2 (UNLP)**, desarrollado en **Java 8** con **Spring Framework**, **Hibernate/JPA** y **MySQL 5.7**, configurado para ejecuciones locales y pruebas contenerizadas con **Docker**.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 8
* **Maven:** 3.8.6
* **Framework:** Spring 5.0.4 (`spring-orm`, `spring-context`, `spring-test`)
* **ORM:** Hibernate 5.4 (`MySQL5InnoDBDialect`)
* **Base de Datos:** MySQL 5.7
* **Testing:** JUnit 5 (Jupiter API 5.0.0)
* **Contenerización:** Docker & Docker Compose

---

## 🐳 Despliegue y Ejecución con Docker

### Prerrequisitos
* Tener instalado **Docker** y **Docker Compose**.

### Instrucciones

1. **Levantar la base de datos MySQL en segundo plano:**
   ```bash
   docker compose run --rm --build app