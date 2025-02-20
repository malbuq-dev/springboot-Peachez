# Peachez Spring Boot Course - Learning Repository

This repository is dedicated to storing and sharing my learning from each YouTube class in the Peachez Spring Boot course.

## Repository Structure

- Each commit corresponds to a class from the course.
- Important topics from each class will be documented in this README.
- Notes may not be as detailed for complete beginners since I am already familiar with backend web development and Spring Boot.
- SWE and Design Patterns concepts may not be covered extensively, as I am already familiar with them.

## Course Notes

### Java Spring Boot [2024] Part 1: Intro, Setup, Controller

#### Key Learnings:
- Setting up the environment with JDK, Spring Boot, and configuring a Docker container for a local database.
- Properly modifying both `pom.xml` and the `application.properties` file to connect a database to a Spring application.
- Gaining deeper insights into Dependency Injection (DI).
- Observing the application of the Strategy Pattern when building Query and Command interfaces for the project's services.

### Java Spring Boot [2024] Part 2: Database, Entity, Repository, Service

#### Key Learnings:
 - A very important thing is the structure of the packages in the app. In general, you want the Main file to be on the same level as the other files.
 - ORM is the idea of mapping Entities to SQL Tables and the @Entity annotation helps with that when creating the entities of your project.
 - @Data is a Lombrok annotation that gives 'free' Getters, Setters and Constructors, very useful when it comes to reducing the verbosity of the codebase in general.
 - Repositories are a layer of abstraction that encapsulate the reading and writting necessities of the API when it comes to the SQL database.
 - DTO's are usefull to abstract useless information about a given entity of the project and they are usually thrown around every where outside of the repository.
 - @PathVariable and @RequestBody are two annotations very useful for the controller side of the project.
 - The trickiest things I found about the controller side of the project was to properly handle the Update and Delete methods. The update method follows the idea of having to create a UpdateXCommand cause of the Command interface only allowing the execute method to have one argument and the Delete will have as it's return Void.

### Java Spring Boot [2024] Part 3: Exception Handling

#### Key Learnings:
 - Exception handlers in Spring can be done in two ways: Building upon the basic error messages from Spring or building costume error messages.
 - The key steps to build upon the basic Spring error messages are: creating a custom run time exception class with a custom message and throwing it in the appropriate services. You'll need the @ResponseStatus to add the correct Http status to the message. Here's a good practice to use Enum and handle all the messages in one place.
 - To build completly custom error messages, you can create a ErrorMessages class with the annotation @ControllerAdvice and then add a custom method with the @ExceptionHandler annotation for every expection you need in your application.