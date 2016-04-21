# Basic Spring Boot Micro Service

This is a starting point for developing a micro service using Spring Boot and Jetty as the web container.

  - Java 8
  - Spring Boot
  - Jetty
  - Gradle
  - Compiles to a jar file
  - All the following commands here are for Linux systems.  I highly recommend Fedora - https://getfedora.org/
  - Simply copy and paste these commands into your terminal and hit return

### Clone the Repo

These commands will create a folder called `MicroServiceProject` in your home directory and will clone the project into that folder.

```sh
cd $HOME; \
mkdir -p MicroServiceProject; \
git clone https://github.com/crazydais/micro-service.git MicroServiceProject
```

### Initial Setup: Make the H2 scripts executable
This is to ensure that these scripts can be executed.
- StartH2TcpServer.sh
- StartH2WebConsole.sh
- StopH2TcpServer.sh
```sh
cd $HOME/MicroServiceProject; \
chmod +x *.sh
```

### Initial Setup: Make a directory for logs and the database
- Logs will be stored in `/logs` with a filename of `spring.log`
- The database will be stored in `/h2db` with a filename of `msdb`
```sh
sudo mkdir /logs; sudo chmod 1777 /logs; \
sudo mkdir /h2db; sudo chmod 1777 /h2db
```

### Build and Run
This command will build and run everything you need to start the service
```sh
cd $HOME/MicroServiceProject; \
gradle clean build -x test; \
./StartH2TcpServer.sh; \
java -jar build/libs/microservice.jar
```

It is also possible to run these commands individually.  Make sure these commands are executed from the root of the project folder.
- To build
```sh
gradle clean build -x test;
```

- To Start the H2 Database Server
```sh
./StartH2TcpServer.sh;
```

- To start Jetty
```sh
java -jar build/libs/microservice.jar 
```

To access the database console, run the `StartH2WebConsole.sh` script.  This will open a web browser window where SQL commands can be executed.
```sh
cd $HOME/MicroServiceProject; \
./StartH2WebConsole.sh
```
Here's an SQL command to get you started.  If there is no data in the database, then this command won't return anything. See the next section for posting data to the database.
```sql
SELECT * FROM customer;
```

### Post some data

Use an application like Postman (https://www.getpostman.com/).  Set the http verb to POST
```
localhost:8080/api/customer/add?firstName=John&lastName=Smith
```

### Get some data
Use an application like Postman (https://www.getpostman.com/).  Set the http verb to GET.
You can also type this URL into your browser, since it is just a GET request. The JSON will be displayed in the browser.
```
localhost:8080/api/customer/getAll
```


### Shutdown Jetty and Stop the H2 Database Server
Jetty can be stopped from the console it is running in by pressing CTRL+C

To stop the H2 database server, run the `StopH2TcpServer.sh` script
```sh
cd $HOME/MicroServiceProject; \
./StopH2TcpServer.sh
```