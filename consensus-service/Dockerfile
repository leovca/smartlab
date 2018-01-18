FROM openjdk:8-jdk-alpine
VOLUME /tmp

WORKDIR /service
ADD ./target/consensus-service-0.0.1-SNAPSHOT.jar /service/service.jar
CMD java -jar -Dspring.profiles.active=docker service.jar