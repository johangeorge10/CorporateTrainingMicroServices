FROM eclipse-temurin:21
WORKDIR /app
COPY /target/*.jar app.jar
COPY central-config-repo /app/central-config-repo
EXPOSE 8888
CMD [ "java" , "-jar" , "app.jar" ]