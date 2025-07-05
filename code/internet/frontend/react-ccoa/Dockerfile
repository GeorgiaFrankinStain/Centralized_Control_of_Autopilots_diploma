FROM alpine
EXPOSE 80
ADD config/default.conf /etc/nginx/http.d/default.conf

COPY . /var/www/localhost/htdocs
RUN apk add nginx && \
    mkdir -p /run/nginx && \
    apk add nodejs && \
    apk add npm && \
    cd /var/www/localhost/htdocs && \
    rm -rf node_modules && \
    npm install --fetch-timeout=60000 && \
    npm run build ;

#    npm install -timeout=6000 && \
#    npm run build -timeout=6000;


RUN apk add --no-cache --upgrade bash

COPY ./docker/build/docker-entrypoint.sh /
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
#CMD ["/bin/sh", "-c", "exec nginx -g 'daemon off;';"]
#WORKDIR /var/www/localhost/htdocs
