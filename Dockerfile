FROM openjdk:8-jdk-alpine
EXPOSE 8082
RUN apk add --no-cache curl
RUN curl -o /eventsProject-1.0.0.jar http://192.168.91.141:8081/repository/maven-releases/tn/esprit/eventsProject/1.0.0/eventsProject-1.0.0.jar
ENTRYPOINT ["java","-jar","/eventsProject-1.0.0.jar"]
