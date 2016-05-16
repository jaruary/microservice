
#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

source db-properties

if [ -z "$H2_PID" ]; then
  printf "\n\n"
  echo "Starting H2 server..."
  java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp -tcpAllowOthers -web -webSSL -webAllowOthers -tcpPort $H2_PORT &

  #e.g.
  #java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp -tcpAllowOthers -web -webSSL -webAllowOthers -tcpPort 9082 &
else
  echo "H2 is already running. PID=$H2_PID"
fi


