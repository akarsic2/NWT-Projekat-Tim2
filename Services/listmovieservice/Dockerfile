FROM openjdk:11-jdk-slim
COPY ./target/movie-0.0.1-SNAPSHOT.jar /usr/src/list-movie-service/
WORKDIR /usr/src/list-movie-service
EXPOSE 8084
CMD ["java", "-jar", "movie-0.0.1-SNAPSHOT.jar"]