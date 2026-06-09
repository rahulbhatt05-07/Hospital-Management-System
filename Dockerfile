# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .

# Agar pom.xml folder ke andar hai toh ye command use karo:
RUN mvn -f HospitalManagementSystem/pom.xml clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app
# JAR file ka path bhi update karo:
COPY --from=build /app/HospitalManagementSystem/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
