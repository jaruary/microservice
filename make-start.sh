#!/usr/bin/env bash

clear
gradle clean build -x test
./h2/StartH2TcpServer.sh
source ./h2/db-properties
java -jar build/libs/microservice.jar 2>&1 1>/dev/null &