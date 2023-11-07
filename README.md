# blackberry-api

# Spring Boot Application with Java 17

This is a Spring Boot application that uses Java 17 and Spring Boot 3.1.15 to connect to a MySQL 8.0 database. The application also includes Swagger documentation for the Spring API.

## Prerequisites

Make sure you have the following components installed before running the application:

- [Eclipse IDE](https://www.eclipse.org/downloads/)
- [OpenJDK 17](https://adoptium.net/?variant=openjdk17&jvmVariant=hotspot)
- [Apache Maven 3.9.5](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Database Configuration

The application connects to a MySQL 8.0 database that is running in a Docker container. There is no need for additional configuration as the database is automatically set up using Docker Compose.

## Running the Application

To run the application in a Docker environment, use the following command in the project's root directory:

```shell
docker-compose up
```

The application will be available at http://localhost:8080.

## Swagger Documentation
API documentation is available through Swagger. You can access the documentation at http://localhost:8080/swagger-ui.html. Here, you can test and explore the API endpoints.

## Building and Packaging the Application
The application is built and packaged using Maven. The Dockerfile copies the source code and the pom.xml file to the container and then runs the mvn clean package command to build the executable JAR file. The resulting JAR is executed as a Spring Boot application.