## Welcome to Measurabl

#### Introduction

This project is intended to test your Java skills with focus on building Web APIs
Refer to the email sent to you on how to complete this assigment

#### Getting to know your project

#### Maven
This is project is built using [Maven](https://maven.apache.org/index.html).
There are many ways to install Maven. The easiest way is to download it, unzip the archive, add it to the system path and run it.

If you are running on a mac: [Install Maven on a Mac](https://www.code2bits.com/how-to-install-maven-on-macos-using-homebrew/)

```bash
brew update
brew search maven
brew info maven
brew install maven
brew cleanup
```

If you are running on Ubuntu:

```bash
sudo apt update
sudo apt install maven
mvn -version
```

If you are running on Windows [Download Maven](https://maven.apache.org/download.cgi)
```bash
unzip apache-maven-3.6.1-bin.zip
# Add Maven to system PATH

# Make sure Java is installed in your system
echo %JAVA_HOME% 
C:\Program Files\Java\jdk1.8.0_172

# Test Maven
mvn -version

```
  
#### How to build and run the project

1. From command line, go to the root of the project
2. `mvn clean install` - this also runs all the unit tests
3. `mvn spring-boot:run` 
 - this invokes the project and binds the server to port 8080 


#### How to invoke and access the default (root) controller
1. From the command line `curl localhost:8080`
2. From a browser http://localhost:8080
3. From Postman `GET http://localhost:8080`

#### Special notes
1. This project uses Lombok to generate boilerplate getters/setters
2. To install [Lombok](https://projectlombok.org/)
3. We strongly recommend [IntelliJ](https://www.jetbrains.com/idea/download/) - community eddition is free

#### H2 enabled
To simplify data access, this project uses an embedded database H2

* Once started, H2 UI can be accessed as follows
1. URL http://localhost:8080/h2-console
2. User name: measurabl
3. Password: measurabl
4. JDBC URL: jdbc:h2:mem:measurabl

#### Swagger enabled
This project has Swagger enabled. This means all endpoints in this project are OpenAPI documented
- HTML formatted documentation -> http://localhost:8080/swagger-ui.html#/
- Machine readable API http://localhost:8080/v2/api-docs 
