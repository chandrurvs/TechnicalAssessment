Technology Specification
------------------------------

Spring Version		 = 5.0.0
Hibernate version 	 = 5.2.11
H2DB Vesion	 	 = 1.4.197
Java Version 		 = 1.8

Project Configuration
------------------------------
1) Created as Mavaen project.
2) Please use java 1.8 Version for Project Build & deploy 
3) Create H2 data base name as vehicledb . if its different name , update the database name hibernate.config.properties.
	Path : 	 src/main/resources
	Example :hibernate.connection.url =jdbc:h2:tcp://localhost/~/vehicledb
4) update the H2 database login details in hibernate.config.properties.
5) update the log folder path in log4j.properties. if not change to catalina home .
     Example : log4j.appender.Appender2.File=C:/sarav/logs/vehicleInvLogs.log

Database Configuration 
-------------------------
1) Install the H2DB 
2)Create the database name as vehicledb
3)create the login h2admin/start@123
4)Execute the DBscripts which is available\VehiclesInventory\DB.txt.

Project Setup
-------------------------
1)Clone or checkout the project from the github.
	github URL :https://github.com/chandrurvs/TechnicalAssessment
2)Open Eclipse or STS tool and import the project.
3)Run as Maven project.
4)Deploy the war into any web server.

