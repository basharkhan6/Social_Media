FROM openjdk:16-jdk-alpine
ARG JAR_FILE=social-media-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]