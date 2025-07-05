#!/bin/sh -eu


function generateConfigJs(){
    echo "{";
    for i in `env | grep '^REACT_APP_'`
    do
        key=$(echo "$i" | cut -d"=" -f1);
        val=$(echo "$i" | cut -d"=" -f2);
        echo "    \"${key}\" : \"${val}\",";
    done
    echo "    \"last_without\" : \"comma\"";
    echo "}";
}
generateConfigJs > /var/www/localhost/htdocs/build/config.json
echo "-- DONE GENERATED CONFIG JSON --"
nginx -g "daemon off;"
