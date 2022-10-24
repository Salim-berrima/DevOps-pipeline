FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} tpAchatProject-1.0.jar
ENTRYPOINT ["java », « -jar », « /tpAchatProject-1.0.jar »]
