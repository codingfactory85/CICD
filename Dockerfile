# Stage 1: Build the application using Maven
FROM maven:3.9.8-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:17-jdk
COPY --from=build /app/target/myapplication.jar /app/myapplication.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapplication.jar"]
