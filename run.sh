#!/bin/bash

cd khs-bjc-servlet
mvn clean package
./run.sh

cd ../khs-bjc-client
mvn clean package
./run.sh

