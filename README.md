# Peachez Spring Boot Course - Learning Repository

This repository is dedicated to storing and sharing my learning from each YouTube class in the Peachez Spring Boot course.

Link to the version of the course I'll be covering here: [text](https://www.youtube.com/watch?v=eHw8bNZ7xi4&list=PL7TZZ2ip0DRCmJ57pzkc3EChRTJ6pm_bH)

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

### Java Spring Boot [2024] Part 4: Validation

#### Key Learnings:
 - Validation it's just the idea that you have to check the data you're receiving to make sure it's formated in the way you defined it should.
 - As a backend API, a Spring App could rely on frontend validation but that's just not good practice and a API should be able to handle validating the data it receives.
 - There are tons of ways of doing that and in this tutorial he teaches two: Custom valitation and Spring validation.
 - Custom validation happens when you throw custom errors with if statements, basically. It's very manual but I liked it.
 - Spring validator is a Spring dependency that at the time of this commit I couldn't make it run to test, but basically handle most of the valitation code via annotations in the entity class fields.

### Java Spring Boot [2024] Part 5: Query String Params, @Query, Spring Data JPA, Repository Methods

#### Key Learnings:
 - The key idea of this class is learning about Spring Data JPA (SDP for short). SDP is basically a Java API that allowes you to perform some fairly complex queries without having to deal with the SQL sintax.
 - To use the SDP you pretty much just have to create a method in the respective repository class you're handling and give a name. The thing is, the name has to follow certain patterns to work with the SDP. So for instance, if you have a 'name' field in your entity, you can do something like findByName and it will work fine.
 - When the query you're looking to do is too complex for SDP to handle, you can do it yourself by just pretty much creating a new method in the repository class and proceeding to add a @Query annotation with the query you want to peform.
 - A important note is that the query has to be written within the JPQL sintax, which obviously looks a lot like SQL, but the catch is that you don't need to worry about the particular sintax of the database you're working with (Postgres in my case), unless you want to (look for nativeQuery=true)
 
### Java Spring Boot [2024] Part 6: JDBC, JPA, Hibernate, Spring Data

#### Key Learnings:
 - Not much notes for this one. This particular class was a really concise and theorical introduction to the Spring stack of data handling. 
 - My main understanding here is that the data comes from a regular database and has to go a long way to get to the Spring Data JPA.
 - In the video he explains that the JDBC is basically the thing we set up in pom.xml, JPA is a bunch of rules that you have to follow in order to be able to read and write data via Java to your database and Hibernate is a way of handling these rules. Spring Data JPA is built on top of these other layers of abstraction and is in general, what we deal with when developing the repositories.
