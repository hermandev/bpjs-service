FROM openjdk:11
MAINTAINER codecorn.id
COPY target/bpjs-service-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
