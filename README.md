
# WAES Assignment  
  
The goal of this assignment is to show your coding skills and what you value in software engineering. We value new ideas so next to the original requirement feel free to improve/add/extend.  
We evaluate the assignment depending on your role (Developer/Tester) and your level of seniority.  
  
# The assignment  
  
- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints  
  - <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right  
- The provided data needs to be diff-ed and the results shall be available on a third end  
  point  
  - <host>/v1/diff/<ID>  
- The results shall provide the following info in JSON format  
  - If equal return that  
  - If not of equal size just return that  
  - If of same size provide insight in where the diffs are, actual diffs are not needed.  
    - So mainly offsets + length in the data  
- Make assumptions in the implementation explicit, choices are good but need to be  
  communicated

# Stack
 - Java 8 ("1.8.0_144")
 - SpringBoot 2
 - Swagger 2 (Api Documentation)
 - H2 (Database)
 - Gradle
# Running the Application

To run the application just execute:

    ./gradlew bootRun 

# Build and Run the Application
To build and run the application separately just execute: 

    ./gradlew bootRun

Then, run the application:

    java -jar build/libs/waes-json-base64-difference-0.0.1-SNAPSHOT.jar

# Testing
To run the tests just execute:

    ./gradlew test 

# Api Documentation

To access the Api Documentation, run the application and go to http://localhost:8080/swagger-ui.html#/diff

# Endpoints

**IMPORTANT:** by default the application will be available in the following url: 

    http://localhost:8080/

To save **LEFT** content by ID:

`curl -X PUT \
  http://localhost:8080/v1/diff/1/left \
  -H 'cache-control: no-cache' \
  -H 'content-type: text/plain' \
  -d eyJhbmRyaSI6IHRydWV9`

To save **RIGHT** content by ID:

`curl -X PUT \
  http://localhost:8080/v1/diff/1/right \
  -H 'cache-control: no-cache' \
  -H 'content-type: text/plain' \
  -d eyJhbmRyaSI6IHRydWV9`

To find **DIFFERENCES** by ID:

`curl -X GET \
  http://localhost:8080/v1/diff/1 \
  -H 'cache-control: no-cache' \`

# Improvements

 - Change to a permanent database (mysql, postgresql, mongodb)
 - Configure Spring profiles for each environment
 - Change the method for find differences to become asynchronous to avoid timeout on high-load
 - Configure cache for improve performance
