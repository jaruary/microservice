#!/usr/bin/env bash

DIR="$(dirname "$0")"
source $DIR/h2/db-properties

java -cp $DIR/h2/bin/h2*.jar org.h2.tools.RunScript \
-url $H2_WEB_URL \
-user $H2_USER \
-password "${H2_SEC}" \
-script $DIR/src/main/resources/sql/initdb.sql
