FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar teb.jar
ENTRYPOINT ["java","-jar","/teb.jar"]