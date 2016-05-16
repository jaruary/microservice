clear
gradle clean build -x test
./StartH2TcpServer.sh
source db-properties
java -jar build/libs/microservice.jar