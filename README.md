**SpringbootWithNeo4j**

SpringbootWithNeo4j is a simple API powered by Neo4j. The project features a basic CRUD API where a user can enroll in a desired course, and each course has several lessons that come with the course - users don't have to register explicitly for each lesson.

**Prerequisites**

Java 20
Maven
Neo4j database
Postman (for API testing)
Familiarity with Neo4j (recommended)
Getting Started

Before testing this project, you need to create an instance from the Neo4j DB and configure the credentials in the Spring Boot application. The next step will be pasting all the queries into the instance you've created in Neo4j. Once you've done all the database setup, you will be able to see the relationship between different nodes. If you're unfamiliar with Neo4j, I would recommend getting familiar with it first before testing out this project - a short video from YouTube should be enough.

**API Endpoints**

Here are the API endpoints available in this project:

getAllCourse
GET: http://localhost:8080/api/v1/courses/

getACourseByIdentifier
GET: http://localhost:8080/api/v1/courses/PL4LFuHwItvKbdK-ogNsOx2X58hHGeQm8c

getUserNameFromDatabase
GET: http://localhost:8080/api/v1/auth/me

enrollCourses
POST: http://localhost:8080/api/v1/enrollments/createEnrollment

responseBody:

            {
            
               "identifier": "PL4LFuHwItvKaOi-bN1E2WUVyZbuRhVokL"
               
            }

getEnrolledCourses
GET: http://localhost:8080/api/v1/enrollments/getEnrolledUser

Feel free to add/test additional feature/endpoints with specific functionality.

**Dependencies**

This project uses a number of dependencies that need to be installed. They are listed in the pom.xml file. Major ones include:

spring-boot-starter-web
spring-boot-starter-data-neo4j
spring-boot-starter-security
neo4j-java-driver
spring-dotenv
spring-boot-devtools
spring-boot-starter-test
Building & Testing

You can build and test this application using Maven. Make sure to have all the required dependencies installed.

**Contributing**

Please feel free to fork this repo, make some changes, and submit pull requests. Bug fixes, feature additions, and updates are welcome!

**Contact**

If you have any queries, please reach out via Linkedin: linkedin.com/in/maulik-davra-40b427164
