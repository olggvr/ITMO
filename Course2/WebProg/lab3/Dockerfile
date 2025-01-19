FROM gradle:8.10-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle war --no-daemon

FROM bitnami/wildfly:latest
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.war /opt/bitnami/wildfly/standalone/deployments/lab3.war
CMD ["/opt/bitnami/scripts/wildfly/run.sh"]