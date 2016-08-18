#!/usr/bin/env bash

DIR="$(dirname "$0")"
source $DIR/h2/db-properties

mkdir -p $DIR/bkup

source $DIR/h2/db-properties; \
java -cp ./h2/bin/h2*.jar org.h2.tools.Script \
-url $H2_WEB_URL \
-user $H2_USER \
-password "${H2_SEC}" \
-script ${DIR}/bkup/"h2db.bkup.$(date '+%A_%d-%b-%Y_%T').sql"
