FROM openjdk:8-jdk-alpine
EXPOSE 8083
VOLUME /tmp
ARG JAR_FILE
ENV LOCAL_DATA=false
ENV KITCHEN_URL=http://188.166.243.95
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]