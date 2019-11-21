# Microservices patterns with Java

A simple architecture to demonstrate the use of key microservice patterns with:
- Spring Boot
- Spring MVC
- Spring Data
- Spring Cloud
- Eureka
- Hystrix
- H2
- Zipkin
- Redis
- Swagger

## Pre requirements to run the applications:

- Docker
- Docker Compose
- Maven

## Installing

Inside each directory:
- java-microservice-patterns-impl/eureka-server
- java-microservice-patterns-impl/movie
- java-microservice-patterns-impl/movie-genres

### Run the command:

```bash
    mvn install dockerfile:build
```

## Running

On directory java-microservice-patterns-impl run the command:
 ```bash
    docker-compose up
```

## Endpoints

- Zipkin: http://localhost:9411
- Eureka: http://localhost:8761
- movie-genres-api: http://localhost:8081/genre
- movie-api: http://localhost:8082/movie

## Documentation

- movie-genres-api: http://localhost:8081/swagger-ui.html
- movie-api: http://localhost:8082/swagger-ui.html