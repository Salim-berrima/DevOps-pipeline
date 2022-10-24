FROM openjdk:11
COPY target/*.jar /
EXPOSE 8090
ENTRYPOINT ["java", "-jar","/tpAchatProject-1.0.jar"]
