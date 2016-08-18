#!/usr/bin/env bash

clear
printf "\nThe 'make-debug' script will...\n* Backup the current database\n* Delete the current database\n* Create and Initilise a new db with test data\n* Start the .jar with debug enabled on port 8000"
printf "\n\n"

DIR="$(dirname "$0")"
source $DIR/h2/db-properties

#printf "\nBacking up the current db...\n"
#$DIR/bkupdb.sh

printf "\nStoping the H2 database...\n"
./stop.sh

printf "\nDeleting the database\n"
rm -rf /h2db/*

gradle clean build -x test
$DIR/h2/StartH2TcpServer.sh

#printf "\n Restoring the db using from Resources/sql/initdb.sql\n"
#$DIR/rstrdb.sh

java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n -jar build/libs/microservice.jar
