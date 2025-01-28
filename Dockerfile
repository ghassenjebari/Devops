FROM openjdk:8-jdk-alpine
ARG VERSION
EXPOSE 8082
RUN apk add --no-cache curl
RUN curl -o /eventsProject-${VERSION}.jar \ http://192.168.91.143:8081/repository/maven-releases/tn/esprit/eventsProject/${VERSION}/eventsProject-${VERSION}.jar

ENTRYPOINT ["java", "-jar", "/eventsProject-${VERSION}.jar"]
