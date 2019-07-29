# jasper-spring-boot-java11-demo

* java 11
* springboots  
* jasper report app
* download pdf/xls from restapi
* data from embedded database hsqldb
* you need to import lombok as well in order to run the program, report will be saved in the project directory

to run the app, do mvn clean install at root project, and start from main class, once it starts
request the report from: localhost:8080/api/reports/pdf  for pdf version , localhost:8080/api/reports/xls for xls version, reports
are saved in the root directory of the project as well
