FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY mi-cita-medica/pom.xml .
COPY mi-cita-medica/src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]