#!/usr/bin/env bash

clear

./h2/StopH2TcpServer.sh

printf "\nDeleting the database...\n"
rm -rf /h2db/*

pushd ./h2 1>/dev/null
source db-properties
popd 1>/dev/null

./h2/StartH2TcpServer.sh

printf "\nBuilding the project...\n"
./gradlew flywayMigrate clean build -x test

printf "\nStarting the server...\n"
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n -jar build/libs/microservice.jar
