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
     docker build -t sparklysimpleapi . && docker run -P --name sparkly sparklysimpleapi
     ```
6. Start sending request by postman!

> Take into consideration that you can run the junit test taking advantage of its embedded Neo4j distribution and avoid the whole issue of having to do all the due prev setup.
   
## Assumptions

Here we detail some of the assumptions made for this small code base.

* Neo4J is configured in another docker image reacheable by this API by sockets.
* There is already a database created and in place.
* There is Docker installed in the machine where the jar is being built.
* There is Java SDK v1.8 at least available in th working machine.
* There is Maven properly installed in the working machine.
* There is an IDE that could be in use. (We suggest IntelliJ Community edition)
* The clients are expecting to receive a JSON kind of response.
* The design has into account several possibilities and approachs. Read the in code comments.
* We wrote this example as a mere base project to write all the other services. You should [read the complete design document](https://github.com/exemartinez/SimpleMovieGraphAPI/blob/master/Project%20Proposal%20-%20Solution%20Architecture.pdf) to understand it completely. 

> <b>Note</b> download the pdf document in order to visualize it appropiately.

## GO Lang

In the folder "go_example" we added three files:

* compile_and_run.sh: a simple bash script with the minimum required commands to compile, and run the example.
* Dockerfile: the docker file that you might need to run the go binary or for a later upload!
* sparklygoapi.go: here goes the simple standalone app, implemented with juest ONE REST service of the ones implemented in the Java version. This is done as a mode of example of what can be achieved in Golang in terms of simplicity. The complete explanation of this file and design can be found in the [pdf](https://github.com/exemartinez/SimpleMovieGraphAPI/blob/master/Project%20Proposal%20-%20Solution%20Architecture.pdf), section "Final Words".



 
