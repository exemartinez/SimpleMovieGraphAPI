# Simple Movie Graph Database API - Implemented as a Docker image
This is a simple base implementation of embedded Neo4j, Spring Boot and Docker.
We can use this a a basis to raise and develop other sort of services.

Here you will find:
* A simple REST service that can be explored by Postman or any other consumer of REST.
* The set of files required to export the complete API into a docker image that can run isolated.
* Automated use cases that proves that the Neo4J structure and queries is solid.

In order to these example base project, you will need to:

1. Clone the github repo https://github.com/exemartinez/SimpleMovieGraphAPI.git
2. Import it directly to IntelliJ or other IDE (batteries included)
3. This is not an stand alone running image, we need to configure it first to connect
against another Neo4J server. You can test this editing resources/application.properties.
4. Run the "packaging" task in Maven.
5. Configure your IDE's plugin or local docker to build and deploy the project.
    * Ex.:
    ```
     docker-compose up --build && docker-compose up -d
     ```
6. Start sending request by postman!

> Take into consideration that you can run the junit test taking advantage of its embedded Neo4j distribution and avoid the whole issue of having to do all the due prev setup.
> Consider to use PostMan and the queries proposed on the folder /postman to test the app.
   
## Assumptions

Here we detail some of the assumptions made for this small code base.

* The Neo4J image deployed by docker-compose will have a user/pass of: neo4j/secret
* There is already a database created and in place.
* There is Docker installed in the machine where the jar is being built.
* There is docker compose in place.
* There is Java SDK v1.8 at least available in th working machine.
* There is Maven properly installed in the working machine.
* There is an IDE that could be in use. (We suggest IntelliJ Community edition)
* The clients are expecting to receive a JSON kind of response.
* The design has into account several possibilities and approachs. Read the in code comments.
* We wrote this example as a mere base project to write all the other services. You should [read the complete design document](https://github.com/exemartinez/SimpleMovieGraphAPI/blob/master/Project%20Proposal%20-%20Solution%20Architecture.pdf) to understand it completely. 

> <b>Note</b> download the pdf document in order to visualize it appropiately.

## GO Lang

We've added a small Golang implementation of some of the ideas planted here. Find it [here]( https://github.com/exemartinez/goRestGraphExample)




 
