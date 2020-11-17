FROM alpine:3.12

# install OpenJDK 11
RUN apk --no-cache add openjdk11

# copy the compiled spring boot application
ADD target/rick-and-morty-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENV _JAVA_OPTIONS "-Xms512m -Xmx2g"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
