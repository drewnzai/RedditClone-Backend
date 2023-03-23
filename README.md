# Reddit Clone

Greetings. 

My name is **Andrew Kombe**, the owner of this project.

This is a personal project that seeks to recreate [Reddit](https://www.reddit.com/) a popular social media site.

This repository has the backend microservice, recreated using Spring Boot.

The app was created in **Kali Linux** 
```bash
Release: 2022.4

Codename: kali-rolling.
```
## Backend

The backend utilises the following technology:
    
  - Java (JDK 11)  
  - Spring Boot Version 2.6.4 (A Java web framework)
  - Maven (A Dependency Management Tool for Java) 
  - JWT (JSON Web Token)
  - MySQL Version 15.1 Distrib 10.6.11-MariaDB (A relational database for data storage and retrieval)
  - AWS (Amazon Web Services): S3 for the upload, storage, and retrieval of files. 

I used JWTs to secure the backend by using signed tokens with each request to use the API. 
The tokens enable the backend to only accept requests from trusted sources, i.e. the app's frontend.

The backend uses Swagger to provide API documentation through the endpoint (**/swagger-ui.html**). 

The project has the following class dependency relationship (you might need to zoom in):

![diagram](Dependency-diagram.png?raw=true)

## Frontend
The frontend is found in [Reddit-frontend](https://github.com/drewnzai/RedditClone-Frontend). 

The Frontend application is an Angular application with all the listed technology information in that repository.