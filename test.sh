#!/usr/bin/env bash

clear

./h2/StopH2TcpServer.sh

printf "\nDeleting the database...\n"
rm -rf /h2db/*

source ./h2/db-properties

./h2/StartH2TcpServer.sh

printf "\nBuilding the project...\n"
./gradlew flywayMigrate clean build jacocoTestReport

./h2/StopH2TcpServer.sh
