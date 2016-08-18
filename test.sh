./stop.sh
rm /h2db/*
./h2/StartH2TcpServer.sh
gradle clean build
