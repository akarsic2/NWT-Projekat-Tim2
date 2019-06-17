FROM openjdk:8-jre-alpine

COPY ./target/service-discovery-0.0.1-SNAPSHOT.jar /usr/src/service-discovery/
WORKDIR /usr/src/service-discovery
EXPOSE 8761
CMD ["java", "-jar", "service-discovery-0.0.1-SNAPSHOT.jar"]