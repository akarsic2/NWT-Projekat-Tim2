FROM openjdk:11-jdk-slim
COPY ./target/movie-genre-service-0.0.1-SNAPSHOT.jar /usr/src/movie-genre-service/
WORKDIR /usr/src/movie-genre-service
EXPOSE 8086
CMD ["java", "-jar", "movie-genre-service-0.0.1-SNAPSHOT.jar"]