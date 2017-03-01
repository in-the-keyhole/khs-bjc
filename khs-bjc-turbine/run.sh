#!/bin/bash

docker rm -fv turbine > /dev/null 2>&1 

docker network create sample > /dev/null 2>&1 

docker run --name turbine --network sample \
    -d -p 8082:8080 \
    -v `pwd`/tomcat-users.xml:/usr/local/tomcat/conf/tomcat-users.xml:Z \
    -v `pwd`/target/khs-bjc-turbine-1.0.0-SNAPSHOT.war:/usr/local/tomcat/webapps/app.war:Z \
    tomcat:8-jre8
