FROM openjdk:11-jdk-slim
COPY ./target/movie-actor-service-0.0.1-SNAPSHOT.jar /usr/src/movie-actor-service/
WORKDIR /usr/src/movie-actor-service
EXPOSE 8081
CMD ["java", "-jar", "movie-actor-service-0.0.1-SNAPSHOT.jar"]