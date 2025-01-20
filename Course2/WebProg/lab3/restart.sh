#!/bin/bash

docker compose down
docker rmi lab3-wildfly
docker compose up --build -d