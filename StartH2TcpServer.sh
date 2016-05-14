
#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

export H2_PID=`netstat -nltup 2>/dev/null | grep -w :::8888 | awk '{print $7}' | grep -o '[0-9]*'`

if [ -z "$H2_PID" ]; then
  echo "Starting H2 server..."
  java -jar h2/bin/h2*.jar -tcp -tcpAllowOthers -tcpPort 8888 &
else
  echo "H2 is already running. PID=$H2_PID"
fi


