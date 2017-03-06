#!/bin/bash

docker rm -fv server > /dev/null 2>&1 

docker network create sample > /dev/null 2>&1 

docker run --name server --network sample -d -p 8081:8080 \
    -v `pwd`/tomcat-users.xml:/usr/local/tomcat/conf/tomcat-users.xml:Z \
    -v `pwd`/target/khs-bjc-servlet-1.0.0-SNAPSHOT.war:/usr/local/tomcat/webapps/app.war:Z \
    -v `pwd`/config.properties:/config.properties:z \
    -e "CATALINA_OPTS=-Darchaius.configurationSource.additionalUrls=file:///config.properties" \
    tomcat:8-jre8 
