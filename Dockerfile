FROM java:8
RUN mkdir /data
VOLUME /data
ADD ./target/stories-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Denv=DEV","/app.jar"]