#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Shutdown the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

source db-properties

if [ -z "$H2_PID" ]; then
  echo "H2 server is not running"
else
  java -cp h2/bin/h2*.jar org.h2.tools.Server -tcpShutdown tcp://127.0.0.1:$H2_PORT

  #e.g.
  #java -cp h2/bin/h2*.jar org.h2.tools.Server -tcpShutdown tcp://127.0.0.1:9082
fi

