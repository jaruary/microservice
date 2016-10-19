#!/usr/bin/env bash

[ -z $BASH ] || shopt -s expand_aliases
alias BEGINCOMMENT="if [ ]; then"
alias ENDCOMMENT="fi"

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

DIR="$(dirname "$0")"
source $DIR/db-properties

if [ -z "$H2_PID" ]; then
  echo "Starting H2 server..."
  java -cp $DIR/bin/h2*.jar org.h2.tools.Server -tcp -tcpPort $H2_TCP_PORT -tcpAllowOthers -web -webPort $H2_WEB_PORT -webSSL -webAllowOthers &
else
  echo "H2 is already running. PID=$H2_PID"
fi


BEGINCOMMENT

Example...

java -cp bin/h2*.jar org.h2.tools.Server -tcp -tcpPort 9092 -tcpAllowOthers \
-web -webPort 8082 -webSSL -webAllowOthers &

ENDCOMMENT
