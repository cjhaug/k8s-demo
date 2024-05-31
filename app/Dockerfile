FROM openjdk:21-jdk
VOLUME /tmp
COPY target/app-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar --debug