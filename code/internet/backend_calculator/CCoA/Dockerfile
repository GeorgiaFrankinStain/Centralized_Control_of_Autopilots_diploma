FROM openjdk:17-alpine3.14
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8080


COPY ./docker/build/docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
