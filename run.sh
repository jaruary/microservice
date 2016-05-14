
gradle clean build -x test; \
./StartH2TcpServer.sh; \
java -jar build/libs/microservice.jar