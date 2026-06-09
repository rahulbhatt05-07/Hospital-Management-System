# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Sab kuch copy karo
COPY . .

# Yahan hum list karke dekh rahe hain ki files kahan hain
RUN ls -R

# Agar pom.xml root mein hai toh ye command chalegi
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app

# Agar build sahi hua, toh jar file yahan hogi
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
