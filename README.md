# Java-OnlineHangman

## Assignment 4: Online Hangman Game
### Description
- This assignment is for CMPT 213 taught at Simon Fraser University in Summer 2020, taught by Victor Cheung
- In this assignment you are going to create an online html-based Hangman game using Spring Boot and Thymeleaf (a template engine that simplifies building of web applications). 
- Players can go to a specific webpage using their web browser to play the game
- Hangman reference [Wikipedia Page] (https://en.wikipedia.org/wiki/Hangman_(game))
- Must respond to commands:
  - GET /welcome
  - *GET /game*\*
  - POST /game
  - GET /game/id
  
*\*note that this page is supposed to be redirected from the “welcome.html” page through a POST request. Directly typing this URL is a GET request and your code doesn’t have to handle it.*
    
### Spring Boot and Thymeleaf
- Spring Boot is a tool for Java to quickly program a web service.
- You will be using the Gradle build system in IntelliJ to import all the necessary dependencies for Spring Boot to work.
- One of the main learning objectives in this assignment is to practice the MVC design pattern, with some class design thinking you need to perform. Hence, we are only going to provide you with some high-level requirements for the game. 
- The Model component is the part where the system represents the data independent on the user interface. Most of the game logic (e.g., check if a guess is correct or not) should be done there.
- The View component is the part where information about how the system presents the data to the user. In this assignment this is going to be the html webpages.
- The Controller component is the part where the handling of commands is done. In this assignment it takes in the HTTP requests from the view, converts them into method calls to the model, and relays the results back to the view. This is supported by Spring Boot using the @Controller annotation.

## Resources
- Reference for creating a [Simple IntelliJ Gradle Project](https://stackoverflow.com/questions/49710330/src-folder-not-created-when-creating-simple-intellij-java-gradle-project)
- JetBrains' guide for [Creating a Project in IntelliJ](https://www.jetbrains.com/help/idea/gradle.html)
- Dr. Brain Fraser's tutorial on [Setting up Spring Boot in IntelliJ](https://www.youtube.com/watch?v=he63dwZdhOM)
- Dr. Brian Fraser's tutorial on [Making a Rest API](https://www.youtube.com/watch?v=rXBsnNCH59o)
- Tutorials for Form Handling using Spring Boot with Thymeleaf"
  - [Atta's Tutorial] (https://attacomsian.com/blog/spring-boot-thymeleaf-form-handling)
  - [Code Java's Tutorial] (https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial)
- (Optional) Tutorial for [Setting up Spring Boot Devtools] (https://howtodoinjava.com/spring-boot2/developer-tools-module-tutorial)
- (Optional) Tutorial for [Setting up the LiveReload plugin] (http://livereload.com/extensions/)   