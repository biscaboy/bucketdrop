# BucketDrop
Utility application for secure file storage to AWS S3 Buckets in the Cloud.

I needed tool to allow clients to upload files to the cloud securely.  I decided to reuse the code from my coursework project [biscaboy/cloudstorage](https://github.com/biscaboy/cloudstorage) to that end so I could avoid a subscription to a cloud storage service.

This project stores files in the actual AWS Cloud.

The project uses these libraries:
[Spring](https://spring.io),
[Spring Boot](https://spring.io/projects/spring-boot),
[Thymeleaf](https://www.thymeleaf.org/) 
the [Spring Initializer](https://start.spring.io/), 
the [MyBatis](https://mybatis.org) ORM (Object-Relational Mapping) framework,
the [JUnit 5](https://junit.org/junit5/) testing framework,
the [Selenium](https://www.selenium.dev/) WebDriver for integration testing and 
the IntelliJ IDE.

## Table of contents
* [Project Requirements](#requirements)
* [Setup](#setup)
* [Resources](#resources)

## Requirements
Identify and authenticate a user.
Allow that user to upload files to an AWS S3 Bucket.

## Setup 
### Launch
The project requires IntelliJ IDE and has only tested in that development environment.
The Maven project file (pom.xml) provides all dependencies.
The main() method is found in:

`bucketdrop/src/main/java/com/davidjdickinson/bucketdrop/BucketDropApplication.java`

### JUnit Tests 
Integration tests on Chrome can be found here:

`
bucketdrop/src/test/java/com/davidjdickinson/bucketdrop/BucketDropApplicationTests.java
`

There are also JUnit tests for the model layer.  They can be found in the `
bucketdrop/src/test/java/com/davidjdickinson/bucketdrop/model/` package.

## Resources
Please see the [biscaboy/cloudstorage](https://github.com/biscaboy/cloudstorage) Readme file for sources used to develop the base code from this applicaiton.  Resources that I used to modify the application to save to the actual cloud are listed below:

TBD.