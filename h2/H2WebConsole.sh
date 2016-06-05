#!/usr/bin/env bash

[ -z $BASH ] || shopt -s expand_aliases
alias BEGINCOMMENT="if [ ]; then"
alias ENDCOMMENT="fi"

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 Web Console
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

DIR="$(dirname "$0")"
source $DIR/db-properties

echo "Starting H2 Web Console - PID: $H2_WEB_CONSOLE_PID"

java -cp $DIR/bin/h2*.jar org.h2.tools.Console \
-driver $H2_DRIVER \
-url $H2_URL \
-user $H2_USER \
-password "${H2_SEC}" \
&


BEGINCOMMENT

Example...

java -cp h2/bin/h2*.jar org.h2.tools.Console -driver "org.h2.Driver" \
-url "jdbc:h2:tcp://127.0.0.1:9092//h2db/msdb;CIPHER=AES" \
-user "admin" \
-password "filepa55 schemapa55" \
2>/dev/null &

ENDCOMMENT
