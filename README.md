# Basic Spring Boot Micro Service

This is a starting point for developing a micro service using Spring Boot and Jetty as the web container.

  - Java 8
  - Spring Boot
  - Jetty
  - Gradle
  - Compiles to a jar file
  - All the following commands here are for Linux systems.  I highly recommend Fedora (https://getfedora.org/).

### Clone the Repo

These commands will create a folder called `MicroServiceProject` in your home directory and will clone the project into that folder.

```sh
cd $HOME \
mkdir -p MicroServiceProject \
git clone https://github.com/crazydais/micro-service.git MicroServiceProject
```

### Initial Setup: Make the H2 scripts executable
This is to ensure that both these scripts can be executed.
- StartH2TcpServer.sh
- StartH2WebConsole.sh
```
cd $HOME/MicroServiceProject \
chmod +x *.sh
```

### Initial Setup: Make a directory for logs and the database
- Logs will be stored in `/logs` with a filename of `spring.log`
- The database will be stored in `/h2db` with a filename of `msdb`
```sh
sudo mkdir /logs; sudo chmod 1777 /logs \
sudo mkdir /h2db; sudo chmod 1777 /h2db
```

### Build and Run
Start the database by running the `StartH2TcpServer.sh` script.  This needs to be running before the application starts.
```
cd $HOME/MicroServiceProject \
./StartH2TcpServer.sh
```
Now build and run the application.
```sh
cd $HOME/MicroServiceProject \
gradle clean build -x test; java -jar build/libs/microservice.jar
```

To access the database console, run the `StartH2WebConsole.sh` script.  This will open a web browser window where SQL commands can be executed.
```
cd $HOME/MicroServiceProject \
./StartH2WebConsole.sh
```

### Post some data

Use an application like Postman (https://www.getpostman.com/).  Set the http verb to POST
```
localhost:8080/api/customer/add?firstName=John&lastName=Smith
```

### Get some data
Use an application like Postman (https://www.getpostman.com/).  Set the http verb to GET.
You can also type this into your browser, since it is just a GET request. The JSON will be displayed in the browser.
```
localhost:8080/api/customer/getAllCustomers
```