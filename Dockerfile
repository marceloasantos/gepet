FROM openjdk:8-jdk-alpine
ARG JAR_FILE=gs-spring-boot-docker/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 2222