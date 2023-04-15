FROM openjdk:17-alpine3.14
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8081
ENTRYPOINT ["java","-jar","target/spring-social-0.0.1-SNAPSHOT.jar"]
