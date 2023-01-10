# Zoo project

Short description

This is the minimalistic Zoo project. It's very simple CRUD application where you can add zones, animal types and animals. It uses Spring Boot, Hibernate and H2 in-memory database.

ERD Diagram

![erd.png](erd.png)

OpenApi Documentation
```
https://app.swaggerhub.com/apis/MrPickle311/Zoo/1.0.0
```
This application is hosted on AWS cluster at address:
```
3.209.7.34:5678/zones/
```

For example if you want to ask for animals in zone
```
http://3.209.7.34:5678/zones/1/animals?size=10&page=0&shouldSortByName=false&sortDirection=ASC
```

How to build it:
```
mvn clean package
```

How to run tests

```
mvn test
```

How to run application

```
java -jar target/zoo.jar
```

How to build as docker image

```
docker build -t zoo .
```

How to run docker container

```
docker run -p 5678:5678 -d zoo
```