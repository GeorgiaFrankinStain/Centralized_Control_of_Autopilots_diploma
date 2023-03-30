FROM openjdk:17-alpine3.14
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/CCoA-0.0.1-SNAPSHOT.jar"]
