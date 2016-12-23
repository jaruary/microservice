# Quick Start Java/Springboot Microservice

This is a starting point for developing a microservice using Spring Boot and Tomcat as the web container.

**Make sure Oracle Hotspot java is installed!**
  - Java    (Oracle Hotspot - v8 LATEST VERSION) - http://www.oracle.com/technetwork/java/javase/downloads/index.html
    and ensure that `JAVA_HOME` has been properly exported in you `$HOME/.bashrc` file.
    
    e.g.
    ```bash
    export JAVA_HOME=/usr/java/default/ 
    export PATH=$JAVA_HOME/bin:$PATH
    ```

**Notes**
  - All the following commands here are for Linux systems.  I highly recommend Fedora - https://getfedora.org/
  - Simply copy and paste these commands, and run them in a terminal window
  - The project will compile to a jar file
  - Configuration for Apache httpd / AJP is available
  - I will do my best to keep this project up-to-date and add new features

### Initial Setup: Clone the Repo

These commands will create a folder called `MicroServiceProject` in your home directory and will clone the project into that folder.

```
cd $HOME; \
mkdir -p MicroServiceProject; \
git clone https://github.com/crazydais/microservice.git MicroServiceProject
```

### Initial Setup: Make all scripts executable
This is to ensure that these scripts can be executed.
- `h2/StartH2TcpServer.sh`
- `h2/StopH2TcpServer.sh`
- `h2/H2WebConsole.sh`
- `debug.sh`
- `start.sh`
- `stop.sh`
- `dbc.sh`

```
cd $HOME/MicroServiceProject; \
chmod +x *.sh h2/StartH2TcpServer.sh h2/StopH2TcpServer.sh h2/H2WebConsole.sh
```

### Initial Setup: Make a directory for logs and the database
- Logs will be stored in `/logs` with a filename of `spring.log`
- The database will be stored in `/h2db` with a filename of `msdb.h2.db`
```
sudo mkdir /logs; sudo chmod 1777 /logs; \
sudo mkdir /h2db; sudo chmod 1777 /h2db
```

### Build and Run
To build and run the project, simply execute the `start.sh` script from the root of your project directory.
Or, if you need to be able to debug the project, run the `debug.sh` script.  Create a remote debugger in your IDE to listen on port 8000.
Both will build and run everything you need to start the service.
```
cd $HOME/MicroServiceProject; \
./start.sh
```
OR
```
cd $HOME/MicroServiceProject; \
./debug.sh
```

Both scripts run FlywayDB, and will create a schema with some basic data.

**Once the service has started, make sure to leave the service running in its own terminal window.
Open another terminal window to run any additional commands.**

### Testing
Some tests have been included, which cover `CustomerContrller.java`, although more tests need to be added.
I hope to add some Jbehave tests in the future.

To run all the current tests, use the following command.
```
cd $HOME/MicroServiceProject; \
./test.sh
```
A jacoco html report will be generated, which will show the code coverage. Open this file in your web browser.
`$HOME/MicroServiceProject/build/reports/jacoco/test/html/index.html`

### H2 Web Console
To access the H2 Web Console, run the `dbc.sh` script from the root of your project directory.  This will open a web browser window where SQL commands can be executed.
```
cd $HOME/MicroServiceProject; \
./dbc.sh
```
FlywayDB will have created some basic data. Here are the SQL commands that were used to create this data in the `customer` table.
```sql
SET SCHEMA microservice;
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Joe','Bloggs');
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Alice','Doe');
INSERT INTO `customer` (`firstname`, `lastname`) VALUES ('Harry','Wizard');
SELECT * FROM customer;
```

When finished with the web console, always click the `Disconnect` icon in the upper left-hand corner to ensure active connections are closed.
Any open connects will retain a lock on the database.

### FlywayDB
Use gradle to execute FlywayDB's migration tool.

```
$   gradle flywayClean flywayMigrate

:flywayClean        <- this will drop all tables
:flywayMigrate      <- this will create the tables and tables, as defined in `src/main/resources/db/migration/V1.0__Create_customer_table.sql`

BUILD SUCCESSFUL

```

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

This means that it is listening on all IPs, on port 9092.
The settings for the H2 database, including port number, username, password, ...etc are defined in `$HOME/MicroServiceProject/h2/db-properties`.
