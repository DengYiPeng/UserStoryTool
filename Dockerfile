FROM 192.168.1.202/common/basejava
RUN mkdir /data
VOLUME /data
ADD ./target/chat-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8002
COPY ./healthchecker.sh /healthchecker.sh
HEALTHCHECK --interval=1m --timeout=3s --retries=1 CMD /healthchecker.sh
ENTRYPOINT ["java","-javaagent:/data/pp-agent/pinpoint-bootstrap-1.6.0.jar","-Dpinpoint.agentId=chat","-Dpinpoint.applicationName=chatservice","-jar","/app.jar"]