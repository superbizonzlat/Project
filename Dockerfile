FROM eclipse-temurin:17-jdk
COPY target/Project-0.0.1-SNAPSHOT.jar client-backend.jar
ENTRYPOINT ["java", "-jar", "client-backend.jar"]
EXPOSE 8080