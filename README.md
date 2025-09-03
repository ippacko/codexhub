# CodexHub


## Stack

- Java 21, Spring Boot 3
- Spring Cloud Gateway
- JWT authentication
- JPA + Flyway
- Postgres and Redis
- OpenAPI docs via springdoc
- Docker and Docker Compose
- GitHub Actions CI
- React admin in `/web`

## Services

- `gateway` routes and checks JWT
- `auth-service` issues JWT, manages users and roles
- `catalog-service` authors and books
- `inventory-service` copies and reservations
- `worker` listens to Redis events and simulates async work

## Quickstart

```bash
make up
# API docs at http://localhost:8080/swagger-ui/index.html
# Login at POST http://localhost:8080/auth/login
```
Default seed user:
```
email: admin@codexhub.local
password: admin123
role: ADMIN
```

## Dev profiles
- Dev uses H2 in memory
- Prod uses Postgres in Compose

See each module `application.yml` for profiles.
