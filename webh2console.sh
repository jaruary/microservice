
#!/usr/bin/env bash

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
#
#                   Start the H2 Web Console
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 

source db-properties

if [ -z "$H2_PORT" ]; then
  echo "ENV not setup! Please source h2Settings.bash first." 
else
  java -cp h2/bin/h2*.jar org.h2.tools.Console -driver $H2_DRIVER -url $H2_URL -user $H2_USER -password $H2_PASS &
  
  #e.g.
  #java -cp h2/bin/h2*.jar org.h2.tools.Console -driver org.h2.Driver -url jdbc:h2:tcp://127.0.0.1:9082//h2db/msdb -user admin -password mypassword123 &s

fi



# Ensure that H2 is using the correct IP for your machine. This may require you to set "/etc/hostname" and "/etc/hosts"
# http://stackoverflow.com/questions/13334407/h2-console-starts-on-ip-address-which-is-not-mine
