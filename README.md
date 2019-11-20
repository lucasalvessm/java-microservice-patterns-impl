# Microservices patterns with Java

A simple application to demonstrate the use of key microservice patterns with:
- Spring Boot
- Spring Data
- Spring Cloud
- Eureka
- Hystrix
- H2

## Pre requirements to run the applications:

- Docker
- Docker Compose
- Maven

### Run the command:

```bash
    mvn install dockerfile:build
```

Inside each directory:
    java-microservice-patterns-impl/eureka-server
    java-microservice-patterns-impl/movie
    java-microservice-patterns-impl/movie-genres

On directory java-microservice-patterns-impl run the command:
 ```bash
    docker-compose up
```
