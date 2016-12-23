#!/usr/bin/env bash

clear
./gradlew clean build -x test
./h2/StartH2TcpServer.sh
source ./h2/db-properties
java -jar build/libs/microservice.jar
