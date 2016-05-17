#!/usr/bin/env bash

[ -z $BASH ] || shopt -s expand_aliases
alias BEGINCOMMENT="if [ ]; then"
alias ENDCOMMENT="fi"

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Shutdown the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

DIR="$(dirname "$0")"
source $DIR/db-properties

if [ -z "$H2_PID" ]; then
  echo "H2 server is not running"
else
  java -cp $DIR/bin/h2*.jar org.h2.tools.Server -tcpShutdown tcp://127.0.0.1:$H2_PORT
fi


BEGINCOMMENT

Example...

java -cp bin/h2*.jar org.h2.tools.Server -tcpShutdown tcp://127.0.0.1:9082

ENDCOMMENT

