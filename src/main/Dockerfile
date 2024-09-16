FROM maven:3.8.5-openjdk-18 AS build
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:18-jre-slim
COPY --from=build /app/target/megogo-test-framework-1.0-SNAPSHOT.jar ./megogo-test-framework.jar
ENTRYPOINT ["java", "-jar", "megogo-test-framework.jar"]