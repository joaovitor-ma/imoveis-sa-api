FROM gradle:jdk17 AS build

WORKDIR /app

COPY . /app

RUN gradle build

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build ./app/build/libs/api-0.0.1-SNAPSHOT.jar ./app/app.jar

ENTRYPOINT ["java", "-jar", "./app/app.jar"]