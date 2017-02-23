#!/bin/bash

docker rm -fv server  > /dev/null
docker rm -fv client  > /dev/null
docker network rm sample > /dev/null
