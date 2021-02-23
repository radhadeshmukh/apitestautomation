# Technical Test for QA Engineer
====================================

This project has one maven module:

* apiautomation

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [RunTestsFromTerminal](#RunTestsFromTerminal)
* [Contacts](#contacts)

## General info

This project is a technical test for QA Engineer.

## Technologies
* Java
* Maven
* Cucumber
* Rest Assured
* AssertJ

## Setup

Note: When you specify clean, you make sure that Maven is going to remove old output before it compiles and packages an application.

```
$ mvn clean install -DskipTests=true
```
### Run Tests From Terminal

> Run all tests and fail at the end.
 >> Run using Maven
```
$ mvn clean install -fn
```
> Run API tests against specifc environment
```
$ mvn -Dtest.env=prod clean install -fn
````

(or)

> If you are using Make

```
$ make test-all
```

## Contacts

Owner: [radhadeshmukh12@gmail.com](radhadeshmukh12@gmail.com)
