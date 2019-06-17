FROM openjdk:8-jre-alpine
COPY ./target/gateway-0.0.1-SNAPSHOT.jar /usr/src/gateway/
WORKDIR /usr/src/gateway
EXPOSE 8089
CMD ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]