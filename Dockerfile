FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/rinhabackend2023-0.0.1-SNAPSHOT.jar ./rinha.jar

EXPOSE 9999

ENTRYPOINT [ "java", "-jar", "./rinha.jar" ]