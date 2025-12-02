FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/TecMarket-0.0.1.jar
COPY ${JAR_FILE} app_tecmarket.jar
EXPOSE 8080
LABEL authors="Edinson"

ENTRYPOINT ["java", "-jar", "app_tecmarket.jar"]