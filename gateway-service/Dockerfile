FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} gateway-service.jar

ENV SPRING_PROFILES_ACTIVE: cloud
ENV ISSUER_URI: http://localhost:8081/auth/realms/my-app
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE: http://localhost:8761/eureka

EXPOSE 8081

ENTRYPOINT ["java","-jar","gateway-service.jar"]