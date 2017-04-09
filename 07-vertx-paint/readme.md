# Purpose
A fun example of using the Vert.x Event Bus to bridge the audience's mobile phone browser to the server and then to a dashboard.  Allows for multi-user Finger Painting. Make sure index.html and dashboard.html are pointing at localhost:8080 and not some other URL that I might have been experimenting with.  

# Run
1) mvn clean compile package

2) java -jar target/07-vertx-paint-1.0-SNAPSHOT.jar

3) Browser #1: http://localhost:8080

4) Browser #2: http://localhost:8080/dashboard.html

### Video: (https://screencast.com/t/8L0PJjqsXr)
