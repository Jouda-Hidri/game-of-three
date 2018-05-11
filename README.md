# Game of three
The two players are two instances of the same RESTful service, communicating using an API.
They call each other using hardcoded urls.
In a bigger system it would also be possible to work with a registry/discovery service (for example Eureka) and use application-names.

The user can call one of the services using a **GET** endpoint.

One of the 2 players would be actively sending (the one who starts) and the other would be simply replying to requests. 
This is done using a Timer with 1 second delay between the requests.
It is possible also to implement this using a while loop.

When the service starts, it sleeps during 1 second, to see if there is any request coming. 
If not, it will send a request to start the game.
In case the 2 players start at the very same millisecond, it is possible to have 2 games going on. 
It is possible to solve the problem using JMS or a database so that the 2 players can share common state. 
With the current implementation, there should be at least some milliseconds time delay between the start of the services.


## Requirements
Java version : 1.8           
Maven version : 3.5.0

## Run the project

````
cd path/to/folder

// Clone the project
git clone https://github.com/Jouda-Hidri/game-of-three.git

// Build and test the application
mvn clean install

// Test the application
mvn test

````
Two instances should be running, one on port 8080 and one on port 8081.
This can be done on two different terminals:

**Terminal A:**
````
cd path/to/folder
cd target
java -jar -Dserver.port=8080 player-0.0.1-SNAPSHOT.jar
````

**Terminal B:**
````
cd path/to/folder
cd target
java -jar -Dserver.port=8081 player-0.0.1-SNAPSHOT.jar
````

To curl an instance over the **GET** endpoint:
````
curl -s http://localhost:8080/number/{number}
````
