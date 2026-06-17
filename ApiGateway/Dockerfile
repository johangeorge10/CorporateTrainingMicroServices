# from maven builder
FROM maven:3.9-eclipse-temurin-21 AS builder
# define workdir
WORKDIR /app
# copy files to container dir
COPY . .

RUN mvn clean package


FROM eclipse-temurin:21 

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
# expose the port 
EXPOSE 8090
# cmd to run 
CMD [ "java" , "-jar" , "app.jar" ]