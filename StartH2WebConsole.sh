
#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Set H2 database settings
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

export DB_HOME=/h2db
export H2_HOME=$(pwd)
export DRIVER=org.h2.Driver
export URL=jdbc:h2:tcp://127.0.0.1:8888//$DB_HOME/msdb
export U=admin
export P=mypassword123

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

java -jar $H2_HOME/h2/bin/h2*.jar -driver $DRIVER -url $URL -user $U -password $P
