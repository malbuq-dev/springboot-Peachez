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

### Java Spring Boot [2024] Part 7: Unit Testing

#### Key Learnings:
 - Unit Testing is pretty much writting code that ensures that some other code does what you'd expect to be doing.
 - There are tons of ways of writing tests but in this video we learn about JUnit, which is the Spring way of doing so.
 - But, do you have to test everything in your program? The answer is no. In general, you should only focus on writing tests to the code you wrote yourself. So basically, you should focus on testing the service layer, cause that's where the core logic of your program reside.
 - For now, my approach for building the tests is:
  - Creating a test class for every service you have
  - Using the annotations @Mock @InjectMock @BeforeEach and @Test, which is well enough explained in the video.
  - Thinking about all the possible paths that the service you're testing could go. So basically, testing for when things go wrong and write.
  - How you name the tests is important and in the video he teaches an approach based on 'given, when, then', which can be translated as: given that the condition X happened, when you run the service you're testing, then Y should happen.
- Three functions were very very important to undestand when it comes to writing these: when, thenReturn, assertEquals, assertThrows.
- The first two functions are important to 'mock' the data you're using for the test. Cause since you're not really connected to the database or client, you'll need to figure out a way of handling the parts of the code that are supposed to connect to one of these, so this functions ensures that a particular function that is present in the service you're testing will return a value according to what you need in the situation of the test. ThenReturn pretty much tells what you want to return when that function is called.
- The remaining ones are pretty similar to each other. They pretty much receive what the result should be and what you'll get based on the input you're giving to the service.
- Important to remember is that when dealing with the assertThrows, you need to pass the Exception.class and a function.

### Java Spring Boot [2024] Part 8: Headers

#### Key Learnings:
- HTTP requests and responses have some data attached to it, obviously, as we learned before. We have the Method, Body, URL and in this class, we learn that we also have a Header.
- Headers are basically a way of sending meta-data in a HTTP request. You send it using key-value pair annotation.
- There are pretty much 3 reasons to use headers, them being:
 - Accept headers, that contains what kind of data you want to get (XML, JSON)
 - Authorization, which has to do with security and will be taught later on the course.
 - Cookies.
- To build the headers using Spring you need the annotation @RequestHeader in you controller, just like you'd do with @RequestBody.
- In order to send the data either in XML or JSON, you'll need the produces parameter in the controller annotation. 
- I'll be honest here, this one didn't provide much context in really what headers are so I had to google it and couldn't learn much more, so these notes are pretty shallow.

### Java Spring Boot [2024] Part 9: Relational Mappings

#### Key Learnings:
- Relational Mapping is the idea of cardinality in databases but applied to entities in Java.
- You pretty much learn how to do OneToOne, ManyToMany and so on, but using JPA.
- The most important things are the annotations. You can honestly just google them cause they're very straight forward, but the most complex on is the @ManyToMany one. You have to build a junction table in your database to use it and use the parameters joinColumns and inverseJoinColumns.

### Java Spring Boot [2024] Part 10: Logging

#### Key Learnings:
- Logs are a good way of helping the dev team understand more about errors, mainly when the app is running in production.
- They're basically print line statements but the powerful part is that, when properly handled, they can give important cues of where some problems are comming from in your app.
- There're a lot of tools for logging, but the one we learn on this class is called SLF4J.
- There are a couple of way to why to log, but the only two we learn about are the error and the info ones.
- To use this Log API, you pretty much just instantiate a static object of the type Logger from the package SLF4J and use it with logger.info or logger.error.
- Important thing is, if you want your app to write the log info to a file in your hard disk, you can by adding a line in your app properties.

### Java Spring Boot [2024] Part 11: Cache

#### Key Learnings:
- Caching is a good way to improve the response time of your API.
- As the other tools we're learning, there are a bunch of Caching APIs that can be used in Spring and we learn about the default one.
- The most interesting thing on this class was knowing that you kinda have put, delete and create operations in caching just like we do in HTTP requests.
- Here's the core of what I learned:
 - When you want to store a result in cache, you need to use @Cachable and add the a name to represent that cache. The name kind of works as a table that stores all the previously called objects by this name.
 - The problem is when you update or delete the data in your database, the cache needs to be updated to represent the current state, so that's the 'stale data' problem.
 - To avoid it, we can simply delete the data from the cache using @CacheEvict in the service that will handle the updating or deletion, and this will remove the updated object from cache, or @CachePut, which will actually change the data in database and then proceed to change it in cache, making them synchronized.
- You can also create your own cache configutarions to over write the default ones from Spring, but I didn't care much about it to be honest.

### Java Spring Boot [2024] Part 12: External API Integration

#### Key Learnings:
- To call an external REST API you have to first build a Configuration file that will create a bean of the type RestTemplate and give you some API calling powers that I can't fully understand right now.
- After that he teaches that is good practice to build response and DTO classes to handle the data that you want to get from the API.
- You of course need to create a service and expose it via a controller.
- In the Service is where the fun things start, cause there are a couple of ways to call the API, but he focus on a particular one that allowes you to use a build pattern to build a URI and then use it after to, via the RestTemplate created in the initial Configuration file, call the API.
- It's important to handle the expections well in these cases cause since it's a third party API you might deal with not getting the data that you're looking for.