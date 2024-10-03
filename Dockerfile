FROM openjdk:17-jdk-alpine
WORKDIR /app
RUN mkdir -p /uploads
RUN chmod 777 /uploads
COPY /target/api-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080