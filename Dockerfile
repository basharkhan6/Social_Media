FROM gradle:7.0.2-jdk16 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:16-jdk-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/social-media-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]


# If dockrize from local jar file
#FROM openjdk:16-jdk-alpine
#RUN mkdir /app
#COPY --from=build /home/gradle/src/build/libs/social-media-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
#ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]