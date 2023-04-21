FROM maven:3.8.6-openjdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/task-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "task-0.0.1-SNAPSHOT.jar"]