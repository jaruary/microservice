#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Shutdown the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

export H2_PID=`netstat -nltup | grep -w :::8888 | awk '{print $7}' | grep -o '[0-9]*'`

if [ -z "$H2_PID" ]; then
  echo "H2 server is not running"
else
  kill $H2_PID
  echo "H2 has been shutdown. PID=$H2_PID"
fi

