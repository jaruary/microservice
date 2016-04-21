# Basic Spring Boot Micro Service

This is a starting point for developing a micro service using Spring Boot and Jetty as the servlet container.

  - Java 8
  - Spring Boot
  - Jetty
  - Gradle
  - Compiles to .jar

### Make a directory for logs

```sh
$ sudo mkdir /logs; sudo chmod 1777 /logs 
```
Logs will be stored here with the file name `spring.log`

### Build and Run
```sh
$ cd <Project Dir>
$ gradle clean build; java -jar build/libs/microservice.jar
```
