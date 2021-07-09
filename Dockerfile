FROM openjdk:16-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/social-media-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]