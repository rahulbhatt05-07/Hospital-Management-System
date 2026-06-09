# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Poori ki poori repo copy karo
COPY . .

# Ab hum wahan se mvn chalayenge jahan pom.xml sach mein hai
# Agar teri root repo mein 'Hospital-Management-System' folder ke andar pom.xml hai:
RUN mvn -f HospitalManagementSystem/pom.xml clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app

# JAR file ko sahi path se copy karo
COPY --from=build /app/HospitalManagementSystem/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
