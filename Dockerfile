FROM amazoncorretto:11-alpine-jdk
MAINTAINER Maty
COPY target/futbol.jar  futbol.jar
ENTRYPOINT ["java","-jar","/futbol.jar"]
