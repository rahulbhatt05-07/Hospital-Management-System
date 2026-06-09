# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build

# Hum seedha us folder mein jayenge jahan pom.xml hai
WORKDIR /app/Hospital-Management-System

# Poora code wahan copy karo
COPY . .

# Ab command chalegi kyunki pom.xml yahi hai
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app

# Build stage se JAR file uthao (path update kar diya hai)
COPY --from=build /app/Hospital-Management-System/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
