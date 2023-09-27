FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","eureka-server.jar"]



