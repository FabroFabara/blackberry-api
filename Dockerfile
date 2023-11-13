# Build stage
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /home/app

# Copy the source code and pom.xml
COPY src /home/app/src
COPY pom.xml /home/app

# Copy the .env file
COPY .env /home/app

# Build the application
RUN mvn clean package

# Expose the port
EXPOSE 8080

# Set environment variables from .env
ARG SPRING_DATASOURCE_URL
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_ROOT_PASSWORD

ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV MYSQL_DATABASE=$MYSQL_DATABASE
ENV MYSQL_USER=$MYSQL_USER
ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

# Entrypoint
ENTRYPOINT ["java", "-jar", "/home/app/target/blackberry-api.jar"]