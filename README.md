# Basic Spring Boot Micro Service

This is a starting point for developing a micro service using Spring Boot and Jetty as the servlet container.

  - Java 8
  - Spring Boot
  - Jetty
  - Gradle
  - Compiles to .jar

### Make a directory for logs and the database

```sh
$ cd /; sudo mkdir /logs; sudo chmod 1777 /logs 
$ cd /; sudo mkdir /h2db; sudo chmod 1777 /h2db
```
Logs will be stored in `/logs` with a filename of `spring.log`
The database will be stored in `/h2db` with a filename of `msdb`

### Build and Run
```sh
$ cd <Project Dir>
$ gradle clean build -x test; java -jar build/libs/microservice.jar
```

### Post some data

Use an application like Postman (https://www.getpostman.com/).  Set the http verb to POST
```
localhost:8080/api/customer/add?firstName=John&lastName=Smith
```


### Get some data
```
localhost:8080/api/customer/getAllCustomers
```