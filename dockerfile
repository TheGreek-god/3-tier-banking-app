# BUILD STAGE
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# RUN STAGE - Using Eclipse Temurin (recommended OpenJDK replacement)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/bankapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]