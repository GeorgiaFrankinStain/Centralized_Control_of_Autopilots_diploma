FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#FROM openjdk:8-jdk-alpine
#ADD build/libs/*.jar dockerapp.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/dockerapp.jar"]