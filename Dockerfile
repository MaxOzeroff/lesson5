FROM eclipse-temurin:17
LABEL authors="max.ozerov"
COPY target/lesson5.jar /app/lesson5.jar
COPY target/libs/* /app/libs/
ENTRYPOINT java ${JAVA_OPTS:-"-XX:MaxRAMPercentage=85.0"} -jar /app/lesson5.jar
EXPOSE 8080