FROM java:8
LABEL maintainer=“leo.garay9@gmail.com”
WORKDIR /app
COPY target/micropanicweb-1.0.1.jar /app/micropanic.jar
ENTRYPOINT ["java","-jar","micropanic.jar"]
