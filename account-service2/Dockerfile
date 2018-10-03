FROM jboss/wildfly
COPY target/account-service2.war /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0","-Djboss.socket.binding.port-offset=100"]
EXPOSE 8180