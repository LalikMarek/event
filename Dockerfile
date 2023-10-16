FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/event-docker.jar event-docker.jar
ENTRYPOINT ["java", "-jar", "event-docker.jar"]