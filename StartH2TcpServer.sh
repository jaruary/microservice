
#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 TCP Server
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

java -jar h2/bin/h2*.jar -tcp -tcpAllowOthers -tcpPort 8888 &
