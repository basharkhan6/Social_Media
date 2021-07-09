FROM openjdk:16-jdk-alpine
ARG JAR_FILE=social-media-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

#EXPOSE does not support by heroku
#EXPOSE 8080
CMD gunicorn --bind 0.0.0.0:$PORT wsgi
ENTRYPOINT java -jar /app.jar --server.port=$PORT