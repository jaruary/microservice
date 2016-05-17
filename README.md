# Basic Spring Boot Microservice Project

This is a starting point for developing a microservice using Spring Boot and Tomcat as the web container.

**Make sure these technologies are installed!**
  - Java    (v8 )
  - Gradle 	(v2.11 or higher)

**Notes**
  - All the following commands here are for Linux systems.  I highly recommend Fedora - https://getfedora.org/
  - Simply copy and paste these commands into your terminal and hit return
  - The project will compile to a jar file
  - Configuration for Apache httpd / AJP is available


### Clone the Repo

These commands will create a folder called `MicroServiceProject` in your home directory and will clone the project into that folder.

```
cd $HOME; \
mkdir -p MicroServiceProject; \
git clone https://github.com/crazydais/microservice.git MicroServiceProject
```

### Initial Setup: Make the H2 scripts executable
This is to ensure that these scripts can be executed.
- StartH2TcpServer.sh
- StopH2TcpServer.sh
- webh2console.sh
- make-run.sh
```
cd $HOME/MicroServiceProject; \
chmod +x *.sh
```

### Initial Setup: Make a directory for logs and the database
- Logs will be stored in `/logs` with a filename of `spring.log`
- The database will be stored in `/h2db` with a filename of `msdb`
```
sudo mkdir /logs; sudo chmod 1777 /logs; \
sudo mkdir /h2db; sudo chmod 1777 /h2db
```

### Build and Run
To simply build and run the project, execute the `make-run.sh` script within a terminal window from the root of your project directory.
It will build and run everything you need to start the service.
```
cd $HOME/MicroServiceProject; \
./make-run.sh
```

This script is just the following command. 
```
cd $HOME/MicroServiceProject; \
gradle clean build -x test; \
./StartH2TcpServer.sh; \
source db-properties \
java -jar build/libs/microservice.jar
```

Of course, these commands can be executed individually.
Make sure these commands are executed within a terminal window from the root of your project directory.

- To build the project
```
gradle clean build -x test;
```

- To Start the H2 Database Server
```
./StartH2TcpServer.sh;
```

- To start Tomcat ( Note: Make sure the H2 TCP server is running before starting Tomcat. Hibernate errors will show if H2 has not been started.)
```
source db-properties \
java -jar build/libs/microservice.jar 
```

To access the database console, run the `webh2console.sh` script.  This will open a web browser window where SQL commands can be executed.
```
cd $HOME/MicroServiceProject; \
./webh2console.sh
```
Here's an SQL command to get you started.
```sql
INSERT INTO `customer` (`first_name`, `last_name`) VALUES ('Zack','Bloggs');
INSERT INTO `customer` (`first_name`, `last_name`) VALUES ('Alice','Doe');
INSERT INTO `customer` (`first_name`, `last_name`) VALUES ('Harry','Wizard');
SELECT * FROM customer;
```

### Get some data

Type this URL into your browser to perform a GET request. The JSON will be displayed in the browser.
```
localhost:8080/api/customer/getAll
```

You can also use an application like Postman (https://www.getpostman.com/).  Set the http verb to `GET`.


### Post some data

Data can be posted to the webservice. Use an application like Postman (https://www.getpostman.com/).  Set the http verb to `POST`
```
localhost:8080/api/customer/add?firstName=John&lastName=Smith
```

### Shutdown Tomcat and Stop the H2 Database Server
Tomcat can be stopped from the console it is running in by pressing `CTRL+C`.  
Tomcat should also be stopped before shutting down the H2 TCP Server.

To stop the H2 TCP Server, run the `StopH2TcpServer.sh` script
```
cd $HOME/MicroServiceProject; \
./StopH2TcpServer.sh
```

### Configuration
This project has been configured in the following way...

- Tomcat Server

`:::8080`

This means that Tomcat is listening on all IPs, on port 8080.  
The settings for Tomcat, including the port number, AJP, ...etc are defined in `application.properties` under the section for Tomcat Server and Tomcat AJP.


- H2 Server

`:::9092 `

This means that it is listening on all IPs, on port 9082.
The settings for the H2 database, including port number, username, password, ...etc are defined in `db-properties`.