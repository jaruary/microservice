#!/usr/bin/env bash

[ -z $BASH ] || shopt -s expand_aliases
alias BEGINCOMMENT="if [ ]; then"
alias ENDCOMMENT="fi"

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

source db-properties

if [ -z "$H2_PID" ]; then
  printf "\n\n"; echo "Starting H2 server..."
  java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp -tcpPort $H2_PORT -tcpAllowOthers -web -webPort $H2_WEB_CONSOLE_PORT -webSSL -webAllowOthers &
else
  echo "H2 is already running. PID=$H2_PID"
fi


BEGINCOMMENT

Example...

java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp -tcpPort 9082 -tcpAllowOthers \
-web -webPort 8082 -webSSL -webAllowOthers &

ENDCOMMENT
