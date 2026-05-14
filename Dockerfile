FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/simplybyte-springboot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
