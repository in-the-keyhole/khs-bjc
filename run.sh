#!/bin/bash

docker rm -fv client > /dev/null 2>&1 

docker network create sample > /dev/null 2>&1 

docker run --name client --network sample -d -p 8080:8080 -v `pwd`/tomcat-users.xml:/usr/local/tomcat/conf/tomcat-users.xml:Z -v `pwd`/target/khs-bjc-client-1.0.0-SNAPSHOT.war:/usr/local/tomcat/webapps/app.war:Z tomcat:8-jre8