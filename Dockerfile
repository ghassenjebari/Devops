FROM openjdk:8-jdk-alpine
ARG VERSION
ENV VERSION=1.0.1


RUN apk add --no-cache curl

# Debugging: Print the version inside the container
RUN echo "DEBUG: VERSION=${VERSION}"

# Debugging: Check if the Nexus repository is accessible
RUN curl -Is http://192.168.91.143:8081/repository/maven-releases/tn/esprit/eventsProject/${VERSION}/eventsProject-${VERSION}.jar || echo "🚨 ERROR: JAR FILE NOT ACCESSIBLE!"

# Ensure the JAR file is downloaded correctly
RUN curl -v -o /eventsProject-${VERSION}.jar http://192.168.91.143:8081/repository/maven-releases/tn/esprit/eventsProject/${VERSION}/eventsProject-${VERSION}.jar || echo "🚨 ERROR: JAR DOWNLOAD FAILED!"

# Debugging: Check if the JAR exists after downloading
RUN ls -lah /eventsProject-* || echo "🚨 ERROR: JAR FILE MISSING!"

ENTRYPOINT ["/bin/sh", "-c", "ls -lah /eventsProject-* && java -jar /eventsProject-${VERSION}.jar"]
