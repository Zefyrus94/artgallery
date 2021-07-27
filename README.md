"Web Information Systems" course project for the 2016-2017 academic year.

#Prerequisites

Make sure you have installed Maven and PostgreSQL>=9.6.19

#Instructions to run the project

1. create a database "artgallery"
 
 psql --username=postgres

 >postgres

 CREATE DATABASE artgallery

2. install [maven](https://maven.apache.org/install.html)
 
3. go to folder artgallery/galleria

4. mvn spring-boot:run

5. verify that your application has started on localhost:8080
