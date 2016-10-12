# Quick Start Java/Springboot Microservice

This is a starting point for developing a microservice using Spring Boot and Tomcat as the web container.

**Make sure these technologies are installed!**
  - Java    (v8 )
  - Gradle 	(v2.11 or higher)

**Notes**
  - All the following commands here are for Linux systems.  I highly recommend Fedora - https://getfedora.org/
  - Simply copy and paste these commands, and run them in a terminal window
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
- `h2/StartH2TcpServer.sh`
- `h2/StopH2TcpServer.sh`
- `h2/H2WebConsole.sh`
- `make-start.sh`
- `stop.sh`
- `webc.sh`

```
cd $HOME/MicroServiceProject; \
chmod +x *.sh h2/StartH2TcpServer.sh h2/StopH2TcpServer.sh h2/H2WebConsole.sh
```

### Initial Setup: Make a directory for logs and the database
- Logs will be stored in `/logs` with a filename of `spring.log`
- The database will be stored in `/h2db` with a filename of `msdb`
```
sudo mkdir /logs; sudo chmod 1777 /logs; \
sudo mkdir /h2db; sudo chmod 1777 /h2db
```

### Build and Run
To build and run the project, simply execute the `make-start.sh` script from the root of your project directory.
It will build and run everything you need to start the service.
```
cd $HOME/MicroServiceProject; \
./make-start.sh
```

**Once the service has started, make sure to leave the service running in its own terminal window.
Open another terminal window to run any additional commands.**

### H2 Web Console
To access the H2 Web Console, run the `webc.sh` script from the root of your project directory.  This will open a web browser window where SQL commands can be executed.
```
cd $HOME/MicroServiceProject; \
./webc.sh
```
Here's an SQL command to get you started.
```sql
SET SCHEMA microservice;
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Joe','Bloggs');
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Alice','Doe');
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Harry','Wizard');
SELECT * FROM customer;
```

When finished, always click the `Disconnect` icon in the upper left-hand corner to  ensure active connections are closed.
Any open connects will retain a lock on the database.

### Get some data

Type this URL into your browser to perform a GET request to the microservice. The JSON will be displayed in the browser.
```
localhost:8080/api/customer/getAll
```

You can also use an application like Postman (https://www.getpostman.com/).  Set the http verb to `GET`.


### Post some data

Using an application like Postman (https://www.getpostman.com/) data can be posted to the webservice.  Set the http verb to `POST`
```
localhost:8080/api/customer/add?firstName=John&lastName=Smith
```

### Shutdown Tomcat and Stop the H2 Database Server
Tomcat can be stopped by pressing `CTRL+C` from within the terminal window it's running in.  Make sure to stop Tomcat before shutting down the H2 TCP Server, as Tomcat relies on the H2 server being available.

To stop the H2 TCP Server, run the `stop.sh` script
```
cd $HOME/MicroServiceProject; \
./stop.sh
```

### Configuration
This project has been configured in the following way...

**Tomcat Server**

`:::8080`

This means that Tomcat is listening on all IPs, on port 8080.  
The settings for Tomcat, including the port number, AJP, ...etc are defined in `$HOME/MicroServiceProject/src/main/resources/application.properties` under the section for Tomcat Server and Tomcat AJP.

**H2 Server**

`:::9092 `

This means that it is listening on all IPs, on port 9082.
The settings for the H2 database, including port number, username, password, ...etc are defined in `$HOME/MicroServiceProject/h2/db-properties`.
