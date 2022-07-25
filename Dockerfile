FROM openjdk:18-alpine
EXPOSE 8080/tcp
COPY /build/libs/monitoringservice-0.0.1-SNAPSHOT.jar monitoring-service.jar
ENTRYPOINT ["java","-jar","/monitoring-service.jar"]