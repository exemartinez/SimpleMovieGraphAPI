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
3. Run the "packaging" task in Maven.
4. Configure your IDE's plugin or local docker to build and deploy the project.
    * Ex.:
    ```
     docker build -t sparklysimpleapi . && docker run -P --name sparkly sparklysimpleapi
     ```
However, this is not an stand alone running image, we need to configure it first to connect
against another Neo4J server. You can test this editing resources/application.properties.
   
#Assumptions
Here we detail some of the assumptions made for this small code base.

* Neo4J is configured in another docker image reacheable by this 
* 


 
