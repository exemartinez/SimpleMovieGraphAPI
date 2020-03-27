FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} sparklysimpleapi.jar
ENTRYPOINT ["java","-jar","/sparklysimpleapi.jar"]