FROM java:8
RUN mkdir /data
VOLUME /data
ADD ./target/stories-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8002
ENTRYPOINT ["java","-javaagent:/data/pp-agent/pinpoint-bootstrap-1.6.0.jar","-Dpinpoint.agentId=stories","-Dpinpoint.applicationName=stories","-jar","/app.jar"]