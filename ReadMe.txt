- To run my original code and test code, download intelliJ IDE, and set up maven in the project.

- Then, make sure to paste this in pom.xml after setting up maven:

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>groupId</groupId>
    <artifactId>PrintTokens</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <properties>
        <maven.compiler.source>18</maven.compiler.source>
        <maven.compiler.target>18</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit-jupiter-version>5.9.0</junit-jupiter-version>
    </properties>
    
</project>

- After that you can simply run the application by pressing the green button near the class name to run all tests, or run each test individually with the green play button to the left. Another way is using command line, which I encountered a lot of issues with. I would prefer to run with the green play button. I have included reports of my coverage for each of the methods. 

-Please contact me if any further questions on compiling the code. 

