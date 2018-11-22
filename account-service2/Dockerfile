FROM java:8-jre
MAINTAINER Michael Geehan <michael.geehan@ericsson.com>

VOLUME /tmp
ARG JAR_FILE
COPY ./target/account-service2-thorntail.jar app.jar
CMD ["java", "-jar", "app.jar", "-Djava.net.preferIPv4Stack=true", "-Djboss.socket.binding.port-offset=100"]

EXPOSE 8080