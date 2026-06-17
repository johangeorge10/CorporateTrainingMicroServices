## from
FROM eclipse-temurin:21-jre
## setup workdir
WORKDIR /app
## copy files
COPY /target/EurekaServer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761
## run command
CMD [ "java" , "-jar" , "app.jar" ]