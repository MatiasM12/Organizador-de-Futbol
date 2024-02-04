#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/futbol.jar futbol.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","futbol.jar"]

