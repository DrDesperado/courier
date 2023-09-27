FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} courier-service.jar

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB courier
ENV POSTGRES_SERVER localhost
ENV POSTGRES_PORT 5444

ENTRYPOINT ["java","-jar","courier-service.jar"]