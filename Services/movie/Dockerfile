FROM openjdk:11-jdk-slim
COPY ./target/movie-0.0.1-SNAPSHOT.jar /usr/src/movie/
WORKDIR /usr/src/movie
EXPOSE 8091
CMD ["java", "-jar", "movie-0.0.1-SNAPSHOT.jar"]